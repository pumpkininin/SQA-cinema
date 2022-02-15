package com.edu.hanu.cinematicketsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "show_seat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ShowSeat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @Column(name = "status")
    private SeatStatus status;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JsonIgnoreProperties(value = {"showSeats"}, allowSetters = true)
    private Show shows;

    @ManyToOne
    @JsonIgnoreProperties(value = {"showSeatSet"}, allowSetters = true)
    private RoomSeat roomSeat;
}


