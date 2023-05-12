package com.michael.paymentService.controller;

import com.michael.paymentService.payload.request.PaymentRequest;
import com.michael.paymentService.payload.response.PaymentResponse;
import com.michael.paymentService.service.PaymentService;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping("/doPayment")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest)  {
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> fetchPaymentDetailsByOrderId(@PathVariable(name = "orderId") Long orderId){
        return new ResponseEntity<>(paymentService.getPaymentDetailsByOrderId(orderId), OK);
    }

}
