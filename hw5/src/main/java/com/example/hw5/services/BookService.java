package com.example.hw5.services;

import com.example.hw5.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> showBooks();

    void insertBook(String name, Long authorId, Long genreId);

    void updateBook(Long id, String name, Long authorId, Long genreId);

    void deleteById(Long id);
}
