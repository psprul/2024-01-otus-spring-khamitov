package com.example.hw5.commands;

import com.example.hw5.entity.Genre;
import com.example.hw5.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Find all genres", key = "ag")
    public String findAllGenres() {
        return genreService.showGenres().stream()
                .map(Genre::toString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }
}
