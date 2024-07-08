package com.example.hw5.repository;

import com.example.hw5.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    Integer hasBookByName(String name);

    Book save(Book book);

    Optional<Book> findById(Long id);

    void deleteById(long id);
}
