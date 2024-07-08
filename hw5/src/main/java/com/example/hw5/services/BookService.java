package com.example.hw5.services;

import com.example.hw5.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> showBooks();

    Book insertBook(String name, Long authorId, Long genreId);

    Book updateBook(Long id, String name, Long authorId, Long genreId);

    void deleteById(Long id);

    Optional<Book> findById(Long id);
}
