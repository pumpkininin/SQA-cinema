package com.edu.hanu.cinematicketsystem;


import com.edu.hanu.cinematicketsystem.model.Genre;
import com.edu.hanu.cinematicketsystem.model.Movie;
import com.edu.hanu.cinematicketsystem.model.Show;
import com.edu.hanu.cinematicketsystem.repository.MovieRepository;
import com.edu.hanu.cinematicketsystem.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.*;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieManagementTest {
    @Mock
    private MovieRepository repository;
    @InjectMocks
    private MovieService service;

    @Test
    public void getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        given(repository.findAll()).willReturn(movies);
        List<Movie> expected = service.getAllMovie();
        assertEquals(expected, movies);
        verify(repository).findAll();
    }

    @Test
    public void updateMovie() {

        Set<Show> shows = new HashSet<>();
        Show show = new Show();
        show.setShowId(1L);
        show.setDate(new Date(System.currentTimeMillis()));
        show.setStartDate(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        show.setEndDate(Timestamp.valueOf("2020-09-23 10:10:10.0"));
        shows.add(show);

        Set<Genre> genres= new HashSet<>();
        Genre genre= new Genre();
        genre.setGenre_id(1L);
        genre.setGenreName("Romance");
        genres.add(genre);

        Movie movie = new Movie(1L, anyString(), anyString(), anyString(), new Date(System.currentTimeMillis()), anyString(), anyInt(),genres,shows);

        Set<Show> newShows = new HashSet<>();
        Show show2 = new Show();
        show.setShowId(1L);
        show.setDate(new Date(System.currentTimeMillis()));
        show.setStartDate(Timestamp.valueOf("2009-09-23 10:10:10.0"));
        show.setEndDate(Timestamp.valueOf("2020-09-23 10:10:10.0"));
        newShows.add(show2);

        Set<Genre> newGenres= new HashSet<>();
        Genre genre2= new Genre();
        genre2.setGenre_id(1L);
        genre2.setGenreName("Horror");
        newGenres.add(genre2);

        Movie newMovie = new Movie(1L, anyString(), anyString(), anyString(), new Date(System.currentTimeMillis()), anyString(), anyInt(),newGenres,newShows);


        //Given
        given(repository.getById(movie.getMovieId())).willReturn(movie);
        //When
        service.updateMovie(movie.getMovieId(), newMovie);
        //Then
        verify(repository).getById(movie.getMovieId());
        verify(repository).saveAndFlush(movie);
    }

    @Test
    public void createMovie() {
        //Given
        Set<Show> shows = new HashSet<>();
        Show show = new Show();
        show.setShowId(1L);
        show.setDate(new Date(System.currentTimeMillis()));
        show.setStartDate(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        show.setEndDate(Timestamp.valueOf("2020-09-23 10:10:10.0"));
        shows.add(show);

        Set<Genre> genres= new HashSet<>();
        Genre genre= new Genre();
        genre.setGenre_id(1L);
        genre.setGenreName("Romance");
        genres.add(genre);

        Movie movie = new Movie(1L, anyString(), anyString(), anyString(), new Date(System.currentTimeMillis()), anyString(), anyInt(),genres,shows);

        //When
        when(repository.saveAndFlush(movie)).thenReturn(movie);
        Movie created = service.addMovie(movie);
        //Then
        assertThat(created.getTitle()).isSameAs(movie.getTitle());
        assertThat(created.getDescription()).isSameAs(movie.getDescription());
        assertThat(created.getThumbnail()).isSameAs(movie.getThumbnail());
        assertThat(created.getReleaseDate()).isSameAs(movie.getReleaseDate());
        assertThat(created.getLanguage()).isSameAs(movie.getLanguage());
        assertThat(created.getDuration()).isSameAs(movie.getDuration());
        assertThat(created.getGenreSet()).isSameAs(movie.getGenreSet());
        assertThat(created.getShowSet()).isSameAs(movie.getShowSet());
        verify(repository).saveAndFlush(movie);
    }

    @Test
    public void getMovieById() {
        Set<Show> shows = new HashSet<>();
        Show show = new Show();
        show.setShowId(1L);
        show.setDate(new Date(System.currentTimeMillis()));
        show.setStartDate(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        show.setEndDate(Timestamp.valueOf("2020-09-23 10:10:10.0"));
        shows.add(show);

        Set<Genre> genres= new HashSet<>();
        Genre genre= new Genre();
        genre.setGenre_id(1L);
        genre.setGenreName("Romance");
        genres.add(genre);

        Movie movie = new Movie(1L, anyString(), anyString(), anyString(), new Date(System.currentTimeMillis()), anyString(), anyInt(),genres,shows);

        when(repository.getById(movie.getMovieId())).thenReturn(movie);
        service.getById(movie.getMovieId());
        verify(repository).getById(movie.getMovieId());
    }


    @Test
    public void deleteMovie() {

        Set<Show> shows = new HashSet<>();
        Show show = new Show();
        show.setShowId(1L);
        show.setDate(new Date(System.currentTimeMillis()));
        show.setStartDate(Timestamp.valueOf("2007-09-23 10:10:10.0"));
        show.setEndDate(Timestamp.valueOf("2020-09-23 10:10:10.0"));
        shows.add(show);

        Set<Genre> genres= new HashSet<>();
        Genre genre= new Genre();
        genre.setGenre_id(1L);
        genre.setGenreName("Romance");
        genres.add(genre);

        Movie movie = new Movie(1L, anyString(), anyString(), anyString(), new Date(System.currentTimeMillis()), anyString(), anyInt(),genres,shows);

        when(repository.findById(movie.getMovieId())).thenReturn(Optional.of(movie));
        service.deleteMovie(movie.getMovieId());
        verify(repository).deleteById(movie.getMovieId());
    }


}
