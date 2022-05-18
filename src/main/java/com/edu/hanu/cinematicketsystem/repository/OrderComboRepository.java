package com.edu.hanu.cinematicketsystem.repository;

import com.edu.hanu.cinematicketsystem.model.OrderCombo;
import com.edu.hanu.cinematicketsystem.model.composite.OrderComboId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderComboRepository extends JpaRepository<OrderCombo, OrderComboId> {
}
