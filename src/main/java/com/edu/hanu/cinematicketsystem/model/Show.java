package com.edu.hanu.cinematicketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shows")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id")
    private Long showId;

    @Column(name = "date")
    private Date date;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties(value = {"showSet"}, allowSetters = true)
    private Movie movie;

    @OneToMany(mappedBy = "shows")
    @JsonIgnoreProperties(value = { "shows" }, allowSetters = true)
    private Set<ShowSeat> showSeats = new HashSet<>();

    @OneToMany(mappedBy = "show")
    @JsonIgnoreProperties(value = { "show" }, allowSetters = true)
    private Set<Order> orderSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties(value = {"show"}, allowSetters = true)
    private Room room;
}
