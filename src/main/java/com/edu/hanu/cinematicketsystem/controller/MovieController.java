package com.edu.hanu.cinematicketsystem.controller;

import com.edu.hanu.cinematicketsystem.model.Movie;
import com.edu.hanu.cinematicketsystem.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/staff/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<Movie>> getAllMovie(){
        return ResponseEntity.ok().body(movieService.getAllMovie());
    }

}
