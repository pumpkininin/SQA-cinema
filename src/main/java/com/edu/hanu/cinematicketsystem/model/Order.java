package com.edu.hanu.cinematicketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Order {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @Column(name = "total_price")
  private double totalPrice;

  @Column(name = "ticket_quantity")
  private int ticketQuantity;

  @OneToMany(mappedBy = "order")
  @JsonIgnoreProperties(value = { "order" }, allowSetters = true)
  private Set<Room> roomSet = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "movie_id")
  @JsonIgnoreProperties(value = {"orderSet"}, allowSetters = true)
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "show_id")
  @JsonIgnoreProperties(value = {"orderSet"}, allowSetters = true)
  private Show show;

  @OneToMany(mappedBy = "order")
  @JsonIgnoreProperties(value = { "order" }, allowSetters = true)
  private Set<RoomSeat> roomSeatsSet = new HashSet<>();

}
