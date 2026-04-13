package com.chandan.orders.system.mapper;

import org.springframework.stereotype.Component;

import com.chandan.orders.system.dto.OrderResponse;
import com.chandan.orders.system.entity.Order;

@Component
public class OrderMapper {
    public OrderResponse toResponse(Order order) {
        return new OrderResponse(
            order.getId(),
            order.getCustomerName(),
            order.getProductName(),
            order.getQuantity(),
            order.getStatus(),
            order.getCreatedAt()
        );
    }
}