package com.edu.hanu.cinematicketsystem.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class LoginRequest {
  @NonNull
  private String username;
  @NonNull
  private String password;
}
