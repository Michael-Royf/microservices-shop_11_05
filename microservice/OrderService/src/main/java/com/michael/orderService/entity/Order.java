package com.michael.orderService.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    private Long id;

    @Column(name = "product_id")
    private Long productId;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "order_date")
    private Instant orderDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "amount")
    private Long amount;
}
