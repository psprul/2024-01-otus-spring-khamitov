package com.example.hw5.repository;

import com.example.hw5.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(Long id);

    Integer hasGenreByName(String name);

    List<Genre> findAll();

    void save(Genre genre);
}
