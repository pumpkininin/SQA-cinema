package com.edu.hanu.cinematicketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_roomSeats",
            joinColumns = { @JoinColumn(name = "room_seat_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id") }
    )
    @JsonIgnore
    private Set<Order> orderSet = new HashSet<>();

    @OneToMany(mappedBy = "roomSeat")
    @JsonIgnore
    private Set<ShowSeat> showSeatSet = new HashSet<>();
}
