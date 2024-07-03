package com.example.hw5.repository;

import com.example.hw5.entity.Genre;

import java.util.List;

public interface GenreRepository {
    Genre findById(Long id);

    Integer hasGenreByName(String name);

    List<Genre> findAll();

    void save(Genre genre);
}
