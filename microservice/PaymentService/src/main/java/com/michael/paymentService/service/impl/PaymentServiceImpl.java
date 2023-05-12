package com.michael.paymentService.service.impl;

import com.michael.paymentService.entity.TransactionDetails;
import com.michael.paymentService.payload.request.PaymentRequest;
import com.michael.paymentService.payload.response.PaymentResponse;
import com.michael.paymentService.repository.TransactionRepository;
import com.michael.paymentService.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper mapper;

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details {}", paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDay(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetails = transactionRepository.save(transactionDetails);
        log.info("Transaction Complited with id: {}", transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(Long orderId) {
        log.info("Getting payment details for the Order Id: {}", orderId);
        TransactionDetails transactionDetails = transactionRepository.findByOrderId(orderId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Transaction Details with order id %s not found", orderId)));
        return mapper.map(transactionDetails, PaymentResponse.class);
    }
}
