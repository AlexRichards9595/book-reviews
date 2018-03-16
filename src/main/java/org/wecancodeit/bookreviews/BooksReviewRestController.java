package org.wecancodeit.bookreviews;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksReviewRestController {
	
	@Resource
	BookRepository bookRepo;
	
	@Resource
	TagRepository tagRepo;

	
	@RequestMapping("/add-tag")
	public Book addTagToThisBook(@RequestParam(value = "id") Long id, @RequestParam String addTag){
		Book thisBook = bookRepo.findOne(id);
		Tag tag = new Tag(addTag);
		tagRepo.save(tag);
		thisBook.addTag(tag);
		bookRepo.save(thisBook);
		
		
		return thisBook;
	}	
	
//	@RequestMapping("/delete-tag")
//	public String DeleteTagFromThisBook(@RequestParam(value = "id") Long id, @RequestParam String delTag){
//		Book thisBook = bookRepo.findOne(id);
//		Tag tag = new Tag(delTag);
//		tagRepo.save(tag);
//		thisBook.addTag(tag);
//		bookRepo.save(thisBook);
//		
//		id = thisBook.getId();
//		
//		return "redirect:/book?id=" + id;
//	}	
		
}
