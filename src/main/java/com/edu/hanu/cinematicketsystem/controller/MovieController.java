package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.model.Movie;
import com.edu.hanu.cinematicketsystem.service.MovieService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/staff/movie")
@CrossOrigin
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovie(){
        return ResponseEntity.ok().body(movieService.getAllMovie());
    }

    @GetMapping("{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable long movieId) {
        return ResponseEntity.ok().body(movieService.getById(movieId));
    }
    @GetMapping(params ={"movieId", "date"})
    public ResponseEntity<Movie> getMovieById(@RequestParam(value = "movieId") long movieId, @RequestParam(value = "date") String date)
        throws ParseException {
        System.out.println(date);
        return ResponseEntity.ok().body(movieService.getByIdAndDate(movieId, date));
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
