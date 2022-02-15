package com.edu.hanu.cinematicketsystem.domain;

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

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @ManyToOne
    @JsonIgnoreProperties(value = {"showSet"}, allowSetters = true)
    private Film film;

    @OneToMany(mappedBy = "shows")
    @JsonIgnoreProperties(value = { "shows" }, allowSetters = true)
    private Set<ShowSeat> showSeats = new HashSet<>();

    @OneToOne
    @JsonIgnoreProperties(value = {"show"}, allowSetters = true)
    private Room room;
}
