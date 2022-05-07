package com.edu.hanu.cinematicketsystem.repository;

import com.edu.hanu.cinematicketsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String role);
}
