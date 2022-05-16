package com.edu.hanu.cinematicketsystem.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComboResponse {
  private Long comboId;
  private String comboName;
  private double price;
  private int quantity;
}
