package com.edu.hanu.cinematicketsystem.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ChangePassword {
  private String currentPass;
  private String newPass;

}
