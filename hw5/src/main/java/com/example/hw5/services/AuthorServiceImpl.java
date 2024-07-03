package com.example.hw5.services;

import com.example.hw5.entity.Author;
import com.example.hw5.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    private void insertOrUpdateWithCheck(Author author) {
        if (authorRepository.hasAuthorByAuthorName(author.getAuthorName()) == 0) {
            authorRepository.save(author);
        } else throw new IllegalArgumentException("Автор с именем " + author.getAuthorName() + " уже существует");
    }

    public void insertAuthor(String authorName) {
        Author author = new Author();
        author.setAuthorName(authorName);
        insertOrUpdateWithCheck(author);
    }

    public void updateAuthor(Long id, String authorName) {
        Author author;
        author = findById(id);
        author.setAuthorName(authorName);
        insertOrUpdateWithCheck(author);
    }

    public List<Author> showAuthors() {
        return authorRepository.findAll();
    }

    public Author findById(Long id) {
        try {
            return authorRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не найден автор с id = " + id);
        }
    }
}
