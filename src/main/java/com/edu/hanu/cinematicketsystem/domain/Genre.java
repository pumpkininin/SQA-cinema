package com.edu.hanu.cinematicketsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genre_id;

    @Column(name = "genre_name")
    private String genreName;

    @ManyToMany(mappedBy = "genreSet")
    @JsonIgnoreProperties(value = {"genreSet"}, allowSetters = true)
    private Set<Film> filmSet = new HashSet<>();
}
