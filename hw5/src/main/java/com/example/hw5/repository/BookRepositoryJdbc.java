package com.example.hw5.repository;

import com.example.hw5.entity.Author;
import com.example.hw5.entity.Book;
import com.example.hw5.entity.Genre;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookRepositoryJdbc implements BookRepository {
    public static final String NAME = "name";
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public List<Book> findAll() {
        return namedParameterJdbcOperations.query("""
                SELECT b.id, b.name, a.id, a.author_name, g.id, g.name
                  FROM books b
                  INNER JOIN genres g ON b.genre_id = g.id
                  INNER JOIN authors a ON b.author_id = a.id""", new BookRowMapper());
    }

    public Integer hasBookByName(String name) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(NAME, name);
        return namedParameterJdbcOperations.queryForObject("""
                        SELECT count(*)
                          FROM books a
                         where a.name = :name"""
                , mapSqlParameterSource, Integer.class);
    }

    public Optional<Book> findById(Long id) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id" , id);
        List<Book> bookList = namedParameterJdbcOperations.query("""
                        SELECT b.id, b.name, a.id, a.author_name, g.id, g.name
                        FROM books b
                        INNER JOIN genres g ON b.genre_id = g.id
                        INNER JOIN authors a ON b.author_id = a.id
                        where b.id = :id"""
                , mapSqlParameterSource, new BookRowMapper());
        return bookList.size() == 1 ? Optional.of(bookList.get(0)) : Optional.empty();
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            var keyHolder = new GeneratedKeyHolder();
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue(NAME, book.getName());
            mapSqlParameterSource.addValue("authorId" , book.getAuthor().getId());
            mapSqlParameterSource.addValue("genreId" , book.getGenre().getId());

            namedParameterJdbcOperations.update("""
                    INSERT INTO books (name, author_id, genre_id)
                    VALUES (:name, :authorId, :genreId)
                    """, mapSqlParameterSource, keyHolder, new String[]{"id"});
            book.setId(keyHolder.getKeyAs(Long.class));
        } else {
            namedParameterJdbcOperations.update("""
                            UPDATE books
                               SET name = :name, author_id = :authorId, genre_id = :genreId
                             WHERE id = :id""",
                    Map.of("id" , book.getId(), NAME, book.getName(), "authorId" , book.getAuthor().getId(), "genreId" , book.getGenre().getId()));
        }
        return book;
    }

    public void deleteById(long id) {
        namedParameterJdbcOperations.update(
                "DELETE FROM books WHERE id = :id" ,
                Map.of("id" , id));
    }

    private static class BookRowMapper implements RowMapper<Book> {
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setId(rs.getLong("authors.id"));
            author.setAuthorName(rs.getString("authors.author_name"));

            Genre genre = new Genre();
            genre.setId(rs.getLong("genres.id"));
            genre.setName(rs.getString("genres.name"));

            Book book = new Book();
            book.setId(rs.getLong("books.id"));
            book.setName(rs.getString("books.name"));
            book.setAuthor(author);
            book.setGenre(genre);
            return book;
        }
    }
}
