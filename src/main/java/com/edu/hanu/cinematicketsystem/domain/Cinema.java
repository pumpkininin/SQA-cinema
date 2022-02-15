package com.edu.hanu.cinematicketsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cinema")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cine_id")
    private Long id;

    @Column(name = "cine_name")
    private String name;

    @Column(name = "total_room")
    private int totalRoom;

    @OneToMany(mappedBy = "cinema")
    @JsonIgnoreProperties(value = {"cinema"}, allowSetters = true)
    private Set<Room> roomSet;
}
