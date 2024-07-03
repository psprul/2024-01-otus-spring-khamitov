package com.example.hw5.services;

import com.example.hw5.entity.Genre;
import com.example.hw5.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        genre = findById(id);
        genre.setName(name);
        insertOrUpdateWithCheck(genre);
    }

    public List<Genre> showGenres() {
        return genreRepository.findAll();
    }

    public Genre findById(Long id) {
        try {
            return genreRepository.findById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Не найден жанр с id = " + id);
        }
    }
}
