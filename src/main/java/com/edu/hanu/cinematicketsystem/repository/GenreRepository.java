package com.edu.hanu.cinematicketsystem.repository;

import com.edu.hanu.cinematicketsystem.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
