package org.wecancodeit.bookreviews;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wecancodeit.bookreviews.Author;
import org.wecancodeit.bookreviews.AuthorRepository;
import org.wecancodeit.bookreviews.Book;
import org.wecancodeit.bookreviews.BookRepository;
import org.wecancodeit.bookreviews.Genre;
import org.wecancodeit.bookreviews.GenreRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class BookReviewsTest {
	@Resource
	private TestEntityManager entityManager;

	@Resource
	private BookRepository bookRepo;

	@Resource
	private GenreRepository genreRepo;

	@Resource
	private AuthorRepository authorRepo;

	@Test
	public void shouldSaveAndLoadBook() {
		Book book = new Book("War and Peace");
		book = bookRepo.save(book);

		long bookId = book.getId();

		entityManager.flush();
		entityManager.clear();

		book = bookRepo.findOne(bookId);

		assertThat(book.getTitle(), is("War and Peace"));
	}

	@Test
	public void shouldSaveAndLoadAuthor() {
		Author author = authorRepo.save(new Author("Hemmingway", "description", "imageUrl"));
		long authorId = author.getId();

		entityManager.flush();
		entityManager.clear();

		author = authorRepo.findOne(authorId);
		assertThat(author.getName(), is("Hemmingway"));
	}

	@Test
	public void shouldSaveBookToGenreRelationship() {

		Genre fiction = new Genre("fiction", "imageUrl");
		genreRepo.save(fiction);
		long genreId = fiction.getId();

		Book warAndPeace = new Book("War and Peace", fiction);
		bookRepo.save(warAndPeace);

		Book inferno = new Book("Inferno", fiction);
		bookRepo.save(inferno);

		entityManager.flush();
		entityManager.clear();

		fiction = genreRepo.findOne(genreId);
		assertThat(fiction.getBooks(), containsInAnyOrder(warAndPeace, inferno));
	}

	@Test
	public void shouldGenerateAuthorId() {
		Author author = authorRepo.save(new Author("Tolstoy", "description", "imageUrl"));
		long authorId = author.getId();

		entityManager.flush();

		assertThat(authorId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishBookToAuthorRelationships() {
		Author tolstoy = authorRepo.save(new Author("tolstoy", "description", "imageUrl"));
		Author dante = authorRepo.save(new Author("dante", "description", "imageUrl"));

		Book book = new Book("One crazy book", tolstoy, dante);
		book = bookRepo.save(book);
		long bookId = book.getId();

		entityManager.flush();
		entityManager.clear();

		book = bookRepo.findOne(bookId);
		assertThat(book.getAuthors(), containsInAnyOrder(tolstoy, dante));

	}

	@Test
	public void shouldEstablishAuthorToBooksRelationship() {
		Author author = authorRepo.save(new Author("Tolstoy", "description", "imageUrl"));
		long authorId = author.getId();

		Book warAndPeace = new Book("War and Peace", author);
		warAndPeace = bookRepo.save(warAndPeace);

		Book annaKarinina = new Book("Anna Karinina", author);
		annaKarinina = bookRepo.save(annaKarinina);

		entityManager.flush();
		entityManager.clear();

		author = authorRepo.findOne(authorId);
		assertThat(author.getBooks(), containsInAnyOrder(warAndPeace, annaKarinina));
	}
}
