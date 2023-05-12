package com.michael.paymentService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "transaction_details")
public class TransactionDetails {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    private Long id;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "mode")
    private String paymentMode;
    @Column(name = "reference_number")
    private String referenceNumber;
    @Column(name = "payment_day")
    private Instant paymentDay;
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "amount")
    private Long amount;
}
