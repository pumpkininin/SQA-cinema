package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.model.Movie;
import com.edu.hanu.cinematicketsystem.model.Show;
import com.edu.hanu.cinematicketsystem.repository.MovieRepository;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.util.CollectionUtils;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  //get all movies
  public List<Movie> getAllMovie() {
    return movieRepository.findAll().stream()
        .filter(movie -> !CollectionUtils.isEmpty(movie.getShowSet()))
        .map(movie ->{
          Set<Show> shows = movie.getShowSet();
          shows = shows.stream()
              .filter(show -> show.getStartTime().compareTo(Timestamp.from(Instant.now())) > 0)
              .collect(Collectors.toSet());
          movie.setShowSet(shows);
          return movie;
        })
        .collect(
            Collectors.toList());
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
    return movieRepository.findById(id).get();
  }
  public Movie getByIdAndDate(Long id, String date) throws ParseException {
    Date requestDate = new SimpleDateFormat("MMM dd yyyy").parse(date);
    Movie movie = movieRepository.findById(id).get();
    Set<Show> availableShow = movie.getShowSet()
        .stream().filter(show -> show.getDate().compareTo(requestDate) == 0)
        .collect(Collectors.toSet());
    movie.setShowSet(availableShow);
    return movie;
  }

}
