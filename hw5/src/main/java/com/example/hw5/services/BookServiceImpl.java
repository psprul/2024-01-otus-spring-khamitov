package com.example.hw5.services;

import com.example.hw5.entity.Book;
import com.example.hw5.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

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

    public Book insertBook(String name, Long authorId, Long genreId) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(authorService.findById(authorId).orElseThrow(() -> new NotFoundException("Не найден автор с id = " + authorId)));
        book.setGenre(genreService.findById(genreId).orElseThrow(() -> new NotFoundException("Не найден жанр с id = " + genreId)));
        if (bookRepository.hasBookByName(book.getName()) > 0) throw new IllegalArgumentException("Книга с именем " + book.getName() + " уже существует");
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, String name, Long authorId, Long genreId) {
        Book book;
        book = findById(id).orElseThrow(() -> new NotFoundException("Не найдена книга с id = " + id));
        book.setName(name);
        book.setAuthor(authorService.findById(authorId).orElseThrow(() -> new NotFoundException("Не найден автор с id = " + id)));
        book.setGenre(genreService.findById(genreId).orElseThrow(() -> new NotFoundException("Не найден жанр с id = " + id)));
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(findById(id).orElseThrow(() -> new NotFoundException("Не найдена книга с id = " + id)).getId());
    }
}
