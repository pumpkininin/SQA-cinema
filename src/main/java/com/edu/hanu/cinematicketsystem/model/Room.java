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
@Table(name = "room")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "seat_total")
    private int seatTotal;

    @Column(name = "room_type")
    private RoomType roomType;

    @OneToMany(mappedBy = "room")
    @JsonIgnoreProperties(value = { "room" }, allowSetters = true)
    private Set<RoomSeat> roomSeats = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = {"roomSet"}, allowSetters = true)
    private Cinema cinema;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_rooms",
            joinColumns = { @JoinColumn(name = "room_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id") }
    )
    @JsonIgnoreProperties(value = {"roomSet"}, allowSetters = true)
    private Set<Order> orderSet = new HashSet<>();

    @OneToMany(mappedBy = "room")
    @JsonIgnoreProperties(value = {"room"}, allowSetters = true)
    private Set<Show> showSet;


}
