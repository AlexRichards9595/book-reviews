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
		Author author = authorRepo.save(new Author("Hemmingway"));
		long authorId = author.getId();
		
		entityManager.flush(); 
		entityManager.clear();
		
		author = authorRepo.findOne(authorId);
		assertThat(author.getName(), is("Hemmingway"));
	}
	

	@Test
	public void shouldSaveBookToGenreRelationship() {

		Genre fiction = new Genre("fiction");
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
		Author author = authorRepo.save(new Author("Tolstoy"));
		long authorId = author.getId();

		entityManager.flush(); 
		
		assertThat(authorId, is(greaterThan(0L)));
	}
	
	@Test
	public void shouldEstablishBookToAuthorRelationships() {
		Author tolstoy = authorRepo.save(new Author("tolstoy"));
		Author dante = authorRepo.save(new Author("dante"));
		
		Book book = new Book("One crazy book", tolstoy, dante);
		book = bookRepo.save(book);
		long bookId = book.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		book = bookRepo.findOne(bookId);
		assertThat(book.getAuthors(), containsInAnyOrder(tolstoy, dante));
		
	}
	
//	@Test
//	public void shouldEstablishTopicToCoursesRelationship() {
//		Topic topic = topicRepo.save(new Topic("Ruby"));
//		long topicId = topic.getId();
//
//		Course ooLanguages = new Course("OO Languages", topic);
//		ooLanguages = courseRepo.save(ooLanguages);
//		
//		Course scriptingLanguages = new Course("Scripting Languages", topic);
//		scriptingLanguages = courseRepo.save(scriptingLanguages);
//		
//		entityManager.flush();
//		entityManager.clear();
//	
//		topic = topicRepo.findOne(topicId);
//		assertThat(topic.getCourses(), containsInAnyOrder(ooLanguages, scriptingLanguages));
//	}
}
