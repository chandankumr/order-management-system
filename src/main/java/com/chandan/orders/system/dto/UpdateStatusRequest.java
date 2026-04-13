package com.chandan.orders.system.dto;

import com.chandan.orders.system.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(
    @NotNull OrderStatus status
) {}
