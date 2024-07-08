package com.example.hw5.repository;

import com.example.hw5.entity.Genre;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class GenreRepositoryJdbc implements GenreRepository {
    public static final String NAME = "name";
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public List<Genre> findAll() {
        return namedParameterJdbcOperations.query(
                "SELECT a.* FROM genres a" , new BeanPropertyRowMapper<>(Genre.class));
    }

    public Integer hasGenreByName(String name) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(NAME, name);
        return namedParameterJdbcOperations.queryForObject("""
                        SELECT count(*)
                          FROM genres a
                         where a.name = :name"""
                , mapSqlParameterSource, Integer.class);
    }

    public Optional<Genre> findById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id" , id);
        List<Genre> genreList = namedParameterJdbcOperations.query("""
                        SELECT a.id id, a.name name
                          FROM genres a
                         where a.id = :id"""
                , mapSqlParameterSource, new BeanPropertyRowMapper<>(Genre.class));
        return genreList.size() == 1 ? Optional.of(genreList.get(0)) : Optional.empty();
    }

    public void save(Genre genre) {
        if (genre.getId() == null) {
            var keyHolder = new GeneratedKeyHolder();
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue(NAME, genre.getName());

            namedParameterJdbcOperations.update("""
                    INSERT INTO genres (name)
                    VALUES (:name)
                    """, mapSqlParameterSource, keyHolder, new String[]{"id"});
            genre.setId(keyHolder.getKeyAs(Long.class));
        } else {
            namedParameterJdbcOperations.update(
                    "UPDATE genres SET name = :name WHERE id = :id" ,
                    Map.of("id" , genre.getId(), NAME, genre.getName()));
        }
    }
}
