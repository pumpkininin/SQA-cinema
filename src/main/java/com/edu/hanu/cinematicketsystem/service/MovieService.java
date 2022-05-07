package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.Movie;
import com.edu.hanu.cinematicketsystem.repository.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    //get all movies
    public List<Movie> getAllMovie(){
        return movieRepository.findAll();
    }
    // create a new movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    //update a movie
    public Movie updateMovie(Long id, Movie movie) {


        //get existing movie in db
        Movie existingMovie = this.movieRepository.getById(id);
        //copy attributes from new movie to existingMovie
        BeanUtils.copyProperties(movie, existingMovie, "id");
        return this.movieRepository.saveAndFlush(existingMovie);
    }

    //delete movie
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    //get movie by id
    public Movie getById(Long id) {
        return this.movieRepository.getById(id);
    }

}
