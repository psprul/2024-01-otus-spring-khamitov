package com.example.hw5.services;

import com.example.hw5.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    void insertAuthor(String authorName);

    void updateAuthor(Long id, String authorName);

    List<Author> showAuthors();

    Optional<Author> findById(Long id);
}
