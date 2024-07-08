package com.example.hw5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;
    private Author author;
    private Genre genre;

    public String toString() {
        return "Id: %d, Name: %s, Author: {%s}, Genre: {%s}".formatted(this.id, this.name, this.author.toString(), this.genre.toString());
    }
}
