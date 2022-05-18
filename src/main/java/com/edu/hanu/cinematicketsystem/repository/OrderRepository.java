package com.edu.hanu.cinematicketsystem.repository;

import com.edu.hanu.cinematicketsystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
