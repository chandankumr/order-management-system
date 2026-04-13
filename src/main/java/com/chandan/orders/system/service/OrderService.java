package com.chandan.orders.system.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.chandan.orders.system.dto.CreateOrderRequest;
import com.chandan.orders.system.dto.OrderResponse;
import com.chandan.orders.system.entity.OrderStatus;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    Page<OrderResponse> getAllOrders(Pageable pageable);
    OrderResponse updateStatus(String id, OrderStatus newStatus);
}