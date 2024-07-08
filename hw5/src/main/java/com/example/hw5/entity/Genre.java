package com.example.hw5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    private Long id;
    private String name;

    public String toString() {
        return "Id: %d, Name: %s".formatted(this.id, this.name);
    }
}
