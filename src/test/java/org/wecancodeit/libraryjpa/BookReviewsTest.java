package org.wecancodeit.libraryjpa;

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

	
//	@Test
//	public void shouldGenerateTopicId() {
//		Topic topic = topicRepo.save(new Topic("its name"));
//		long topicId = topic.getId();
//
//		entityManager.flush(); // forces jpa to hit the db when we try to find it
//		
//		assertThat(topicId, is(greaterThan(0L)));
//	}
//	
//	@Test
//	public void shouldEstablishCourseToTopicsRelationships() {
//		// topic is not the owner, so we save these first
//		Topic java = topicRepo.save(new Topic("Java"));
//		Topic ruby = topicRepo.save(new Topic("Ruby"));
//		
//		Course course = new Course("OO Languages", java, ruby);
//		course = courseRepo.save(course);
//		long ooLanguagesId = course.getId();
//		
//		entityManager.flush();
//		entityManager.clear();
//		
//		course = courseRepo.findOne(ooLanguagesId);
//		assertThat(course.getTopics(), containsInAnyOrder(java, ruby));
//		
//	}
//	
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
