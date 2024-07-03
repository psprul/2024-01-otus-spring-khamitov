package com.example.hw5.services;

import com.example.hw5.entity.Genre;

import java.util.List;

public interface GenreService {
    void insertGenre(String name);

    void updateGenre(Long id, String name);

    List<Genre> showGenres();

    Genre findById(Long id);
}
