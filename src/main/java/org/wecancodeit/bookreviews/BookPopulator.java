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
		
		Genre fiction = new Genre("fiction");
		fiction = genreRepo.save(fiction);
		Genre nonFiction = new Genre("non-fiction");
		nonFiction = genreRepo.save(nonFiction);
		
		Author tolstoy = new Author("Leo Tolstoy");
		tolstoy = authorRepo.save(tolstoy);
		Author tolkein = new Author("J.R.R. Tolkein");
		tolkein = authorRepo.save(tolkein);
		Author lewis = new Author("C.S. Lewis");
		lewis = authorRepo.save(lewis);
		
		Book warAndPeace = bookRepo.save(new Book("War and Peace", fiction, "Its a really long but good book", tolstoy));
		Book fellowship = bookRepo.save(new Book("Fellowship of the Ring", fiction, "It's alright", tolkein));
		Book twoTowers = bookRepo.save(new Book("The Two Towers", fiction, "It's a little better", tolkein));
		Book returnOfTheKing = bookRepo.save(new Book("The Return of the King", fiction, "It's the best", tolkein));
		Book surprisedByJoy = bookRepo.save(new Book("Surprised By Joy", nonFiction, "It is a little dry", lewis));
	}


}
