package org.wecancodeit.bookreviews;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
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


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class BookReviewsTest {
	@Resource
	private TestEntityManager entityManager;

	@Resource
	private BookRepository bookRepo;

	@Resource
	private TagRepository tagRepo;

	@Resource
	private AuthorRepository authorRepo;

	@Test
	public void shouldSaveAndLoadBook() {
		Book book = new Book("War and Peace", null, null, null);
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
	public void shouldSaveBookToAuthorRelationship() {

		Author tolstoy = new Author("tolstoy", "Russian", "imageUrl");
		authorRepo.save(tolstoy);
		long genreId = tolstoy.getId();

		Book warAndPeace = new Book("War and Peace", tolstoy, null, null);
		bookRepo.save(warAndPeace);

		Book inferno = new Book("Inferno", tolstoy, null, null);
		bookRepo.save(inferno);

		entityManager.flush();
		entityManager.clear();

		tolstoy = authorRepo.findOne(genreId);
		assertThat(tolstoy.getBooks(), containsInAnyOrder(warAndPeace, inferno));
	}

	@Test
	public void shouldGenerateAuthorId() {
		Author author = authorRepo.save(new Author("Tolstoy", "description", "imageUrl"));
		long authorId = author.getId();

		entityManager.flush();

		assertThat(authorId, is(greaterThan(0L)));
	}

	@Test
	public void shouldEstablishBookToTagRelationships() {
		Book warAndPeace = new Book("War and Peace", null, null, null);
		warAndPeace = bookRepo.save(warAndPeace);
		long bookId = warAndPeace.getId();
		
		Tag historical = tagRepo.save(new Tag("historical", warAndPeace));
		Tag fiction = tagRepo.save(new Tag("fiction", warAndPeace));

		entityManager.flush();
		entityManager.clear();

		warAndPeace = bookRepo.findOne(bookId);
		assertThat(warAndPeace.getTags(), containsInAnyOrder(historical, fiction));

	}

	@Test
	public void shouldEstablishTagToBooksRelationship() {

		Book warAndPeace = new Book("War and Peace", null, null, null);
		warAndPeace = bookRepo.save(warAndPeace);

		Book annaKarinina = new Book("Anna Karinina", null, null, null);
		annaKarinina = bookRepo.save(annaKarinina);
		
		Tag fiction = tagRepo.save(new Tag("fiction", warAndPeace, annaKarinina));
		long tagId = fiction.getId();

		entityManager.flush();
		entityManager.clear();

		fiction = tagRepo.findOne(tagId);
		assertThat(fiction.getBooks(), containsInAnyOrder(warAndPeace, annaKarinina));
	}
	
	@Test
	public void shouldAddBookToTag() {
		Tag fiction = tagRepo.save(new Tag("fiction"));
		Book warAndPeace = new Book("War and Peace", null, null, null);
		
		fiction.addBook(warAndPeace);
		
		assertThat(fiction.getBooks(), contains(warAndPeace));
	}
	@Test
	public void shouldRemoveBookFromTag() {
		Book warAndPeace = new Book("War and Peace", null, null, null);
		Tag fiction = tagRepo.save(new Tag("fiction", warAndPeace));
		
		fiction.delBook(warAndPeace);
		
		assertThat(fiction.getBooks(), not(hasItem(warAndPeace)));
	}
}
