package com.example.hw5;

import com.example.hw5.entity.Author;
import com.example.hw5.entity.Book;
import com.example.hw5.entity.Genre;
import com.example.hw5.repository.BookRepositoryJdbc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BookRepositoryJdbc.class)
@Slf4j
class BookRepositoryJdbcTest {
	public static final int EXPECTED_BOOKS_COUNT = 1;
	public static final String EXPECTED_BOOK = "Капитанская дочка";

	@Autowired
	private BookRepositoryJdbc bookRepositoryJdbc;

	@Test
	@DisplayName("Возвращать ожидаемое количество книг в БД")
	void shouldReturnExpectedBookCount() {
		assertThat(bookRepositoryJdbc.findAll()).hasSize(EXPECTED_BOOKS_COUNT);
	}

	@Test
	@DisplayName("Проверяем наличие книги Капитанская дочка")
	void hasBookByNameTest(){
		assertThat(bookRepositoryJdbc.hasBookByName(EXPECTED_BOOK)).isEqualTo(1);
	}

	Book testBook(){
		Book book = new Book();
		book.setName("Test book");
		Author author = new Author();
		author.setId(1L);
		author.setAuthorName("Test");
		book.setAuthor(author);
		Genre genre = new Genre();
		genre.setId(1L);
		genre.setName("Test");
		book.setGenre(genre);
		return book;
	}

	@Test
	@DisplayName("Проверяем функционал добавления книги")
	void test(){
		bookRepositoryJdbc.save(testBook());
		assertThat(bookRepositoryJdbc.hasBookByName(testBook().getName())).isEqualTo(1);
	}
}
