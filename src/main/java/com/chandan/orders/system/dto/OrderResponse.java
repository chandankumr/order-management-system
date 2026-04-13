package com.chandan.orders.system.dto;

import com.chandan.orders.system.entity.OrderStatus;
import java.time.LocalDateTime;

public record OrderResponse(
    String id,
    String customerName,
    String productName,
    int quantity,
    OrderStatus status,
    LocalDateTime createdAt
) {}
