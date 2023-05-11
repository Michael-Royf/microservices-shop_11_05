package com.michael.orderService.service;

import com.michael.orderService.payload.request.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
