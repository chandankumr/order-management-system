package com.chandan.orders.system.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chandan.orders.system.dto.CreateOrderRequest;
import com.chandan.orders.system.dto.OrderResponse;
import com.chandan.orders.system.entity.Order;
import com.chandan.orders.system.entity.OrderStatus;
import com.chandan.orders.system.exception.InvalidStatusTransitionException;
import com.chandan.orders.system.exception.OrderNotFoundException;
import com.chandan.orders.system.mapper.OrderMapper;
import com.chandan.orders.system.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Explicit state transition map
    private static final Map<OrderStatus, Set<OrderStatus>> ALLOWED_TRANSITIONS = Map.of(
        OrderStatus.PLACED,  Set.of(OrderStatus.SHIPPED),
        OrderStatus.SHIPPED, Set.of(OrderStatus.DELIVERED)
    );

    @Override
    public OrderResponse createOrder(CreateOrderRequest req) {
        Order order = new Order(
            req.customerName(),
            req.productName(), 
            req.quantity()
        );
        return mapper.toResponse(repository.save(order));
    }

    @Override
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public OrderResponse updateStatus(String id, OrderStatus newStatus) {
        Order order = repository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        validateTransition(order.getStatus(), newStatus);
        order.setStatus(newStatus);
        order.setUpdatedAt(LocalDateTime.now());
        return mapper.toResponse(repository.save(order));
    }

    private void validateTransition(OrderStatus current, OrderStatus next) {
        Set<OrderStatus> allowed = ALLOWED_TRANSITIONS.getOrDefault(current, Set.of());
        if (!allowed.contains(next)) {
            throw new InvalidStatusTransitionException(current, next);
        }
    }
}