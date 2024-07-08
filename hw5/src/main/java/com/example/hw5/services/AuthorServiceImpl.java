package com.example.hw5.services;

import com.example.hw5.entity.Author;
import com.example.hw5.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public void insertAuthor(String authorName) {
        Author author = new Author();
        author.setAuthorName(authorName);
        if (authorRepository.countByName(author.getAuthorName()) > 0) throw new IllegalArgumentException("Автор с именем " + author.getAuthorName() + " уже существует");
        authorRepository.save(author);
    }

    public void updateAuthor(Long id, String authorName) {
        Author author;
        author = findById(id).orElseThrow(() -> new NotFoundException("Не найден автор с id = " + id));
        author.setAuthorName(authorName);
        if (authorRepository.countByName(author.getAuthorName()) > 0) throw new IllegalArgumentException("Автор с именем " + author.getAuthorName() + " уже существует");
        authorRepository.save(author);
    }

    public List<Author> showAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
}
