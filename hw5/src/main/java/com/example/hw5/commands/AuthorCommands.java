package com.example.hw5.commands;

import com.example.hw5.entity.Author;
import com.example.hw5.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class AuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "Find all authors", key = "aa")
    public String findAllAuthors() {
        return authorService.showAuthors().stream()
                .map(Author::toString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }
}
