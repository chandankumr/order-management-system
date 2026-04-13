package com.chandan.orders.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chandan.orders.system.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {}
