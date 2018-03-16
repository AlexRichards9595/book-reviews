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
		
		Tag fiction = new Tag("Fiction");
		Tag nonFiction = new Tag("Non-Fiction");
		Tag historical = new Tag("Historical");
		Tag biographical = new Tag("biographical");
		Tag epic = new Tag("Epic");
		fiction = tagRepo.save(fiction);
		nonFiction = tagRepo.save(nonFiction);
		historical = tagRepo.save(historical);
		biographical = tagRepo.save(biographical);
		epic = tagRepo.save(epic);

		Author tolstoy = new Author("Leo Tolstoy", "He is Russion AF", "./images/leo-tolstoy.jpg");
		tolstoy = authorRepo.save(tolstoy);
		Author tolkein = new Author("J.R.R. Tolkein", "He is british AF", "./images/jrr-tolkein.jpg");
		tolkein = authorRepo.save(tolkein);
		Author lewis = new Author("C.S. Lewis", "A cheery ole chap", "./images/cs-lewis.JPG");
		lewis = authorRepo.save(lewis);
		Author dostoyevsky = new Author("Fyodor Dostoyevsky", "Also is very Russian, and sad",
				"./images/fyodor-dostoyevsky.jpg");
		dostoyevsky = authorRepo.save(dostoyevsky);

		Book warAndPeace = bookRepo.save(new Book("War and Peace", tolstoy, "./images/war-and-peace.jpg",
				"It's a really long but good book", fiction, historical));
		Book fellowship = bookRepo
				.save(new Book("Fellowship of the Ring", tolkein, "./images/fellowship.gif", "It's alright", fiction, epic));
		Book twoTowers = bookRepo
				.save(new Book("The Two Towers", tolkein, "./images/two-towers.gif", "It's a little better", fiction, epic));
		Book returnOfTheKing = bookRepo.save(new Book("The Return of the King", tolkein,
				"./images/return-of-the-king.gif", "It's the best", fiction, epic));
		Book surprisedByJoy = bookRepo.save(
				new Book("Surprised By Joy", lewis, "./images/surprised-by-joy.jpg", "It is a little dry", nonFiction, biographical));
		Book crimeAndPunishment = bookRepo.save(new Book("Crime and Punishment", dostoyevsky,
				"./images/crime-and-punishment.png", "It's my favorite book", fiction));

	}

}
