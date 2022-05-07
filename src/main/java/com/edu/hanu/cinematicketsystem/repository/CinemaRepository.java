package com.edu.hanu.cinematicketsystem.repository;

import com.edu.hanu.cinematicketsystem.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
