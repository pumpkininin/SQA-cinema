package com.edu.hanu.cinematicketsystem.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;

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

  @OneToMany(mappedBy = "combo")
  @JsonIgnore
  Set<OrderCombo> orderComboSet;

}
