package com.chandan.orders.system.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateOrderRequest(
    @NotBlank String customerName,
    @NotBlank String productName,
    @Min(1) int quantity
) {}