package com.chandan.orders.system.exception;

import com.chandan.orders.system.entity.OrderStatus;

public class InvalidStatusTransitionException extends RuntimeException {
    public InvalidStatusTransitionException(OrderStatus current, OrderStatus next) {
        super("Invalid status transition from " + current + " to " + next);
    }
}
