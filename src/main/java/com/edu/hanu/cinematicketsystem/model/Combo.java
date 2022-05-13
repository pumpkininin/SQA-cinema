package com.edu.hanu.cinematicketsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "combo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Combo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "combo_id")
  private Long comboId;

  @Column(name = "combo_name")
  private String comboName;

  @Column(name = "combo_price")
  private double price;


}
