package org.wecancodeit.bookreviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BookPopulator implements CommandLineRunner {


	
	@Resource
	private BookRepository bookRepo;
	
	@Resource
	private GenreRepository genreRepo;
	
	@Resource
	private AuthorRepository authorRepo;
	
	@Override
	public void run(String...args) throws Exception {
		
		Genre fiction = new Genre("Fiction", "./images/fiction.jpg");
		fiction = genreRepo.save(fiction);
		Genre nonFiction = new Genre("Nonfiction", "./images/nonfiction.jpg");
		nonFiction = genreRepo.save(nonFiction);
		
		Author tolstoy = new Author("Leo Tolstoy", "He is Russion AF", "./images/leo-tolstoy.jpg");
		tolstoy = authorRepo.save(tolstoy);
		Author tolkein = new Author("J.R.R. Tolkein", "He is british AF", "./images/jrr-tolkein.jpg");
		tolkein = authorRepo.save(tolkein);
		Author lewis = new Author("C.S. Lewis", "A cheery ole chap", "./images/cs-lewis.JPG");
		lewis = authorRepo.save(lewis);
		Author dostoyevsky = new Author("Fyodor Dostoyevsky", "Also is very Russian, and sad", "./images/fyodor-dostoyevsky.jpg");
		dostoyevsky = authorRepo.save(dostoyevsky);
		
		Book warAndPeace = bookRepo.save(new Book("War and Peace", fiction, "./images/war-and-peace.jpg", "It's a really long but good book", tolstoy));
		Book fellowship = bookRepo.save(new Book("Fellowship of the Ring", fiction, "./images/fellowship.gif", "It's alright", tolkein));
		Book twoTowers = bookRepo.save(new Book("The Two Towers", fiction, "./images/two-towers.gif", "It's a little better", tolkein));
		Book returnOfTheKing = bookRepo.save(new Book("The Return of the King", fiction, "./images/return-of-the-king.gif", "It's the best", tolkein));
		Book surprisedByJoy = bookRepo.save(new Book("Surprised By Joy", nonFiction, "./images/surprised-by-joy.jpg", "It is a little dry", lewis));
		Book crimeAndPunishment = bookRepo.save(new Book("Crime and Punishment", fiction, "./images/crime-and-punishment.png", "It's my favorite book", dostoyevsky));

}


}
