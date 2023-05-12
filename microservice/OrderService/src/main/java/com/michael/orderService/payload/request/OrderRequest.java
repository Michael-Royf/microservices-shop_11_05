package com.michael.orderService.payload.request;

import com.michael.clients.payment.PaymentMode;
import lombok.*;



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
