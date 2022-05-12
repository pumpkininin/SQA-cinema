package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.Role;
import com.edu.hanu.cinematicketsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  @Autowired
  private RoleRepository roleRepository;

  public Role findByName(String roleName) {
    return roleRepository.findByRole(roleName).get();
  }
}
