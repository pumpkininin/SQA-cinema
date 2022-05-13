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
@Table(name = "movie")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;

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

    @Column(name = "trailerId")
    private String trailerId;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "movie_genres",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    @JsonIgnoreProperties(value = {"movieSet"}, allowSetters = true)
    Set<Genre> genreSet = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    @JsonIgnoreProperties(value = { "movie" }, allowSetters = true)
    private Set<Show> showSet = new HashSet<>();

    @OneToMany(mappedBy = "movie")
    @JsonIgnoreProperties(value = { "movie" }, allowSetters = true)
    private Set<Order> orderSet = new HashSet<>();
}
