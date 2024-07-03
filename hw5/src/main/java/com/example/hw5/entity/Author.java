package com.example.hw5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Long id;
    private String authorName;

    public String toString() {
        return "Id: %d, AuthorName: %s".formatted(this.id, this.authorName);
    }
}
