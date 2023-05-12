package com.michael.clients.payment;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentService {

    @PostMapping("/api/v1/payment/doPayment")
     Long doPayment(@RequestBody PaymentRequest paymentRequest);
}
