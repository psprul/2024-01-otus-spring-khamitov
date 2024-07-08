package com.example.hw5.services;

import com.example.hw5.entity.Genre;
import com.example.hw5.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    private void insertOrUpdateWithCheck(Genre genre) {
        if (genreRepository.hasGenreByName(genre.getName()) == 0) {
            genreRepository.save(genre);
        } else throw new IllegalArgumentException("Жанр с наименованием " + genre.getName() + " уже существует");
    }

    public void insertGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        insertOrUpdateWithCheck(genre);
    }

    public void updateGenre(Long id, String name) {
        Genre genre;
        genre = findById(id).orElseThrow(() -> new NotFoundException("Не найдена жанр с id = " + id));
        genre.setName(name);
        insertOrUpdateWithCheck(genre);
    }

    public List<Genre> showGenres() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }
}
