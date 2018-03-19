package org.wecancodeit.bookreviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookPopulator implements CommandLineRunner {

	@Resource
	private BookRepository bookRepo;

	@Resource
	private AuthorRepository authorRepo;

	@Resource
	private TagRepository tagRepo;

	@Override
	public void run(String... args) throws Exception {

		Author tolstoy = new Author("Leo Tolstoy", "He is Russion AF", "./images/leo-tolstoy.jpg");
		tolstoy = authorRepo.save(tolstoy);
		Author tolkein = new Author("J.R.R. Tolkein", "He is british AF", "./images/jrr-tolkein.jpg");
		tolkein = authorRepo.save(tolkein);
		Author lewis = new Author("C.S. Lewis", "A cheery ole chap", "./images/cs-lewis.JPG");
		lewis = authorRepo.save(lewis);
		Author dostoyevsky = new Author("Fyodor Dostoyevsky", "Also is very Russian, and sad",
				"./images/fyodor-dostoyevsky.jpg");
		dostoyevsky = authorRepo.save(dostoyevsky);

		Book warAndPeace = bookRepo.save(
				new Book("War and Peace", tolstoy, "./images/war-and-peace.jpg", "It's a really long but good book"));
		Book fellowship = bookRepo
				.save(new Book("Fellowship of the Ring", tolkein, "./images/fellowship.gif", "It's alright"));
		Book twoTowers = bookRepo
				.save(new Book("The Two Towers", tolkein, "./images/two-towers.gif", "It's a little better"));
		Book returnOfTheKing = bookRepo
				.save(new Book("The Return of the King", tolkein, "./images/return-of-the-king.gif", "It's the best"));
		Book surprisedByJoy = bookRepo
				.save(new Book("Surprised By Joy", lewis, "./images/surprised-by-joy.jpg", "It is a little dry"));
		Book crimeAndPunishment = bookRepo.save(new Book("Crime and Punishment", dostoyevsky,
				"./images/crime-and-punishment.png", "It's my favorite book"));

		Tag fiction = new Tag("Fiction", warAndPeace, fellowship, twoTowers, returnOfTheKing, crimeAndPunishment);
		Tag nonFiction = new Tag("Non-Fiction", surprisedByJoy);
		Tag historical = new Tag("Historical", warAndPeace);
		Tag biographical = new Tag("biographical", surprisedByJoy);
		Tag epic = new Tag("Epic", fellowship, twoTowers, returnOfTheKing);
		fiction = tagRepo.save(fiction);
		nonFiction = tagRepo.save(nonFiction);
		historical = tagRepo.save(historical);
		biographical = tagRepo.save(biographical);
		epic = tagRepo.save(epic);
	}
}
