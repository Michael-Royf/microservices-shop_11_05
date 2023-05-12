package com.michael.paymentService.service;

import com.michael.paymentService.payload.request.PaymentRequest;
import com.michael.paymentService.payload.response.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(Long orderId);
}
