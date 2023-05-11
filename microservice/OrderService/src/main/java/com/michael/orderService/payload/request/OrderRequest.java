package com.michael.orderService.payload.request;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequest {
    private Long productId;
    private long totalAmount;
    private Long quantity;
    private PaymentMode paymentMode;
}
