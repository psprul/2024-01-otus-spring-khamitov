package com.example.hw5.services;

import com.example.hw5.entity.Author;

import java.util.List;

public interface AuthorService {
    void insertAuthor(String authorName);

    void updateAuthor(Long id, String authorName);

    List<Author> showAuthors();

    Author findById(Long id);
}
