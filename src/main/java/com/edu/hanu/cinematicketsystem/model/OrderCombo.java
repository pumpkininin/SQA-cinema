package com.edu.hanu.cinematicketsystem.model;

import com.edu.hanu.cinematicketsystem.model.composite.OrderComboId;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_combo")
public class OrderCombo {
  @EmbeddedId
  private OrderComboId id;

  @Column(name = "quantity")
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "order_id")
  @MapsId(value = "orderId")
  @JsonIgnore
  private Order order;

  @ManyToOne
  @JoinColumn(name = "combo_id")
  @MapsId(value = "comboId")
  @JsonIgnore
  private Combo combo;
}
