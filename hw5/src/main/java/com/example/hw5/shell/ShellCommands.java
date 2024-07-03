package com.example.hw5.shell;

import com.example.hw5.entity.Author;
import com.example.hw5.entity.Book;
import com.example.hw5.entity.Genre;
import com.example.hw5.services.AuthorService;
import com.example.hw5.services.BookService;
import com.example.hw5.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    @ShellMethod(value = "New author" , key = {"na"})
    public void insertAuthor(String authorName) {
        authorService.insertAuthor(authorName);
    }

    @ShellMethod(value = "Update authors" , key = {"ua"})
    public void updateAuthors(Long id, String authorName) {
        authorService.updateAuthor(id, authorName);
    }

    @ShellMethod(value = "Show authors" , key = {"sa"})
    public String showAuthors() {
        return authorService.showAuthors().stream()
                .map(Author::toString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "New genre" , key = {"ng"})
    public void insertGenre(String genreName) {
        genreService.insertGenre(genreName);
    }

    @ShellMethod(value = "Update genre" , key = {"ug"})
    public void updateGenre(Long id, String genreName) {
        genreService.updateGenre(id, genreName);
    }

    @ShellMethod(value = "Show genres" , key = {"sg"})
    public String showGenres() {
        return genreService.showGenres().stream()
                .map(Genre::toString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "New book" , key = {"nb"})
    public void insertBook(String name, Long authorId, Long genreId) {
        bookService.insertBook(name, authorId, genreId);
    }

    @ShellMethod(value = "Update book" , key = {"ub"})
    public void updateBook(Long id, String name, Long authorId, Long genreId) {
        bookService.updateBook(id, name, authorId, genreId);
    }

    @ShellMethod(value = "Delete book" , key = {"db"})
    public void deleteBook(Long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Show books" , key = {"sb"})
    public String showBooks() {
        return bookService.showBooks().stream()
                .map(Book::toString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

}
