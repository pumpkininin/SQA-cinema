package com.edu.hanu.cinematicketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

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


  @ManyToOne
  @JoinColumn(name = "movie_id")
  @JsonIgnoreProperties(value = {"orderSet"}, allowSetters = true)
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "show_id")
  @JsonIgnoreProperties(value = {"orderSet"}, allowSetters = true)
  private Show show;

  @ManyToMany(mappedBy = "orderSet")
  @JsonIgnoreProperties(value = { "orderSet" }, allowSetters = true)
  private Set<RoomSeat> roomSeatsSet = new HashSet<>();

  @ManyToMany(mappedBy = "orderSet")
  @JsonIgnoreProperties(value = {"orderSet"}, allowSetters = true)
  private Set<Room> roomSet = new HashSet<>();

}
