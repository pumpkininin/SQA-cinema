package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.model.Movie;
import com.edu.hanu.cinematicketsystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/staff/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovie(){
        return ResponseEntity.ok().body(movieService.getAllMovie());
    }

    @GetMapping("{movieId}")
    public Movie getMovieById(@PathVariable long movieId) {
        return this.movieService.getById(movieId);
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return this.movieService.addMovie(movie);
    }

    @PutMapping("{movieId}")
    public Movie updateMovie(@PathVariable Long movieId, @RequestBody Movie movie) {
        return this.movieService.updateMovie(movieId,movie);
    }


    @DeleteMapping("{movieId}")
    public void deleteMovie(@PathVariable Long movieId) {
        this.movieService.deleteMovie(movieId);
    }

}
