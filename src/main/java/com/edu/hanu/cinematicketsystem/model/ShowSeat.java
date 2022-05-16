package com.edu.hanu.cinematicketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    @JsonIgnore
    private Show shows;

    @ManyToOne
    @JoinColumn(name = "room_seat_id")
    @JsonIgnore
    private RoomSeat roomSeat;
}


