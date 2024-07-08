package com.example.hw5.repository;

import com.example.hw5.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findById(Long id);

    Integer countByName(String authorName);

    List<Author> findAll();

    Author save(Author author);
}
