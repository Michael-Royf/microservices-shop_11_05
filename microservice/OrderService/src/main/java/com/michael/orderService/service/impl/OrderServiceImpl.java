package com.michael.orderService.service.impl;

import com.michael.orderService.entity.Order;
import com.michael.orderService.payload.request.OrderRequest;
import com.michael.orderService.repository.OrderRepository;
import com.michael.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper mapper;


    @Override
    public Long placeOrder(OrderRequest orderRequest) {

        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        order = orderRepository.save(order);
        log.info("Order Places successfully with Order Id: {}", order.getId());

        return order.getId();
    }
}
