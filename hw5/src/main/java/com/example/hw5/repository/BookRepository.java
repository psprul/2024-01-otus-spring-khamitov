package com.example.hw5.repository;

import com.example.hw5.entity.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();

    Integer hasBookByName(String name);

    void save(Book book);

    Book findById(Long id);

    void deleteById(long id);
}
