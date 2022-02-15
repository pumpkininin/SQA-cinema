package com.edu.hanu.cinematicketsystem.service;

import com.edu.hanu.cinematicketsystem.domain.Film;
import com.edu.hanu.cinematicketsystem.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilm(){
        return filmRepository.findAll();
    }
    public void updateFilm(){

    }
}
