package com.example.hw5.services;

import com.example.hw5.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    void insertGenre(String name);

    void updateGenre(Long id, String name);

    List<Genre> showGenres();

    Optional<Genre> findById(Long id);
}
