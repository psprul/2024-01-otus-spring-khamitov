package com.example.hw5.repository;

import com.example.hw5.entity.Author;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AuthorRepositoryJdbc implements AuthorRepository {
    public static final String AUTHOR_NAME = "authorName";
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public List<Author> findAll() {
        return namedParameterJdbcOperations.query(
                "SELECT a.* FROM authors a" , new BeanPropertyRowMapper<>(Author.class));
    }

    public Integer hasAuthorByAuthorName(String authorName) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(AUTHOR_NAME, authorName);
        return namedParameterJdbcOperations.queryForObject("""
                        SELECT count(*)
                          FROM authors a
                         where a.author_name = :authorName"""
                , mapSqlParameterSource, Integer.class);
    }

    public Author findById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id" , id);
        return namedParameterJdbcOperations.queryForObject("""
                        SELECT a.id id, a.author_name authorName
                          FROM authors a
                         where a.id = :id"""
                , mapSqlParameterSource, new BeanPropertyRowMapper<>(Author.class));
    }

    public void save(Author author) {
        if (author.getId() == null) {
            var keyHolder = new GeneratedKeyHolder();
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue(AUTHOR_NAME, author.getAuthorName());

            namedParameterJdbcOperations.update("""
                    INSERT INTO authors (author_name)
                    VALUES (:authorName)
                    """, mapSqlParameterSource, keyHolder, new String[]{"id"});
            author.setId(keyHolder.getKeyAs(Long.class));
        } else {
            namedParameterJdbcOperations.update(
                    "UPDATE authors SET author_name = :authorName WHERE id = :id" ,
                    Map.of("id" , author.getId(), AUTHOR_NAME, author.getAuthorName()));
        }
    }
}
