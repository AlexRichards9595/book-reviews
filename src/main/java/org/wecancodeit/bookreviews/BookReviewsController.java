package org.wecancodeit.bookreviews;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookReviewsController {

	@Resource
	BookRepository bookRepo;
	
	@Resource
	GenreRepository genreRepo;
	
	@Resource 
	AuthorRepository authorRepo;
	
	@RequestMapping(value = "/books")
	public String getAllBooks(Model model) {
		model.addAttribute("books", bookRepo.findAll());
		return "books";
	}
	
	@RequestMapping("/book")
	public String getABook(@RequestParam Long id, Model model) {
		model.addAttribute("books", bookRepo.findOne(id));
		return "book";
	}
	@RequestMapping(value = "/genres")
	public String getAllGenres(Model model) {
		model.addAttribute("genres", genreRepo.findAll());
		return "genres";
	}
	@RequestMapping("/genre")
	public String getAGenre(@RequestParam Long id, Model model) {
		model.addAttribute("genres", genreRepo.findOne(id));
		return "genre";
	}
	@RequestMapping(value = "/authors")
	public String getAllAuthors(Model model) {
		model.addAttribute("authors", authorRepo.findAll());
		return "authors";
	}
	@RequestMapping("/author")
	public String getAnAuthor(@RequestParam Long id, Model model) {
		model.addAttribute("authors", authorRepo.findOne(id));
		return "author";
	}
	public String showBook(long bookId, Model model) {
		return null;
	}

	public void showAllBooks(Model model) {
		
	}
}
