package com.edu.hanu.cinematicketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room_seat")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RoomSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_seat_id")
    private Long id;

    @Column(name = "seat_location")
    private String seatLocation;

    @Column(name = "seat_type")
    private SeatType seatType;

    @ManyToOne
    @JsonIgnoreProperties(value = {"roomSeats"}, allowSetters = true)
    private Room room;

    @ManyToOne
    @JsonIgnoreProperties(value = {"roomSeatsSet"}, allowSetters = true)
    private Order order;

    @OneToMany(mappedBy = "roomSeat")
    @JsonIgnoreProperties(value = { "roomSeat" }, allowSetters = true)
    private Set<ShowSeat> showSeatSet = new HashSet<>();
}
