package com.edu.hanu.cinematicketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "film")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "realease_date")
    private Date releaseDate;

    @Column(name = "language")
    private String language;

    @Column(name = "duration")
    private int duration;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "film_genres",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    @JsonIgnoreProperties(value = {"filmSet"}, allowSetters = true)
    Set<Genre> genreSet = new HashSet<>();

    @OneToMany(mappedBy = "film")
    @JsonIgnoreProperties(value = { "film" }, allowSetters = true)
    private Set<Show> showSet = new HashSet<>();

    public Movie(long l, String anyString, String anyString1, String anyString2, Date date, String anyString3, int anyInt) {
    }
}
