package com.edu.hanu.cinematicketsystem.repository;

import com.edu.hanu.cinematicketsystem.domain.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
