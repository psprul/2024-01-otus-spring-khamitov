package com.example.hw5.services;

import com.example.hw5.entity.Book;
import com.example.hw5.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;

    public List<Book> showBooks() {
        return bookRepository.findAll();
    }

    private void insertOrUpdateWithCheck(Book book) {
        if (bookRepository.hasBookByName(book.getName()) == 0) {
            bookRepository.save(book);
        } else throw new IllegalArgumentException("Книга с именем " + book.getName() + " уже существует");
    }

    public void insertBook(String name, Long authorId, Long genreId) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(authorService.findById(authorId));
        book.setGenre(genreService.findById(genreId));
        insertOrUpdateWithCheck(book);
    }

    public void updateBook(Long id, String name, Long authorId, Long genreId) {
        Book book;
        book = findById(id);
        book.setName(name);
        book.setAuthor(authorService.findById(authorId));
        book.setGenre(genreService.findById(genreId));
        insertOrUpdateWithCheck(book);
    }

    private Book findById(Long id) {
        try {
            return bookRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не найдена книга с id = " + id);
        }
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(findById(id).getId());
    }
}
