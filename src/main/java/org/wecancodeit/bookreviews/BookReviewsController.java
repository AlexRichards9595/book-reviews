package org.wecancodeit.bookreviews;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class BookReviewsController {

	@Resource
	BookRepository bookRepo;
	
	@RequestMapping(value = "/books")
	public String getAllBooks(Model model) {
		model.addAttribute("books", bookRepo.findAll());
		return "books";
	}
	
	@RequestMapping("/book-review")
	public String getABook(@RequestParam Long id, Model model) {
		model.addAttribute("books", bookRepo.findOne(id));
		return "book";
	}

	public String showBook(long bookId, Model model) {
		return null;
	}

	public void showAllBooks(Model model) {
		
	}
}
