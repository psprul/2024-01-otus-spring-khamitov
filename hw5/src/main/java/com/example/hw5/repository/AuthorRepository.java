package com.example.hw5.repository;

import com.example.hw5.entity.Author;

import java.util.List;

public interface AuthorRepository {
    Author findById(Long id);

    Integer hasAuthorByAuthorName(String authorName);

    List<Author> findAll();

    void save(Author author);
}
