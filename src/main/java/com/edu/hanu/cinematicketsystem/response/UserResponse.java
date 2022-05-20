package com.edu.hanu.cinematicketsystem.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserResponse {
  private String username;
  private String fullname;
  private List<String> roles;
}
