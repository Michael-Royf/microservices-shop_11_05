package com.michael.orderService.service.impl;

import com.michael.clients.payment.PaymentRequest;
import com.michael.clients.payment.PaymentResponse;
import com.michael.clients.payment.PaymentService;
import com.michael.clients.product.ProductResponse;
import com.michael.clients.product.ProductService;
import com.michael.orderService.entity.Order;
import com.michael.orderService.payload.request.OrderRequest;
import com.michael.orderService.payload.response.OrderResponse;
import com.michael.orderService.repository.OrderRepository;
import com.michael.orderService.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final RestTemplate restTemplate;


    @Override
    public Long placeOrder(OrderRequest orderRequest) {
//check quantity
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());

        log.info("Creating Order with Status CREATED");
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);


        log.info("Calling Payment Service to complete the payment");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();
        String orderStatus = null;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully, Changing the Order status to PLACED");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error occured in payment. Changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        log.info("Order Places successfully with Order Id: {}", order.getId());
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetailsById(Long orderId) {
        log.info("Get order details for Order id: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %s not found", orderId)));

        OrderResponse orderResponse = mapper.map(order, OrderResponse.class);


        log.info("Invoking Product service to fetch the product for id: {}", order.getProductId());
        ProductResponse productResponse
                = restTemplate.getForObject(
                "http://product-service/api/v1/product/productId/" + order.getProductId(),
                ProductResponse.class
        );
        log.info("Getting payment information form the payment Service");
        PaymentResponse paymentResponse
                = restTemplate.getForObject(
                "http://payment-service/api/v1/payment/" + order.getId(),
                PaymentResponse.class
        );
        orderResponse.setPaymentResponse(paymentResponse);
        orderResponse.setProductResponse(productResponse);
        return orderResponse;
    }
}
