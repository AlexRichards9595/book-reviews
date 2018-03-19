package org.wecancodeit.bookreviews;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksReviewRestController {

	@Resource
	BookRepository bookRepo;

	@Resource
	TagRepository tagRepo;

	@RequestMapping("/add-tag")
	public Book addTagToThisBook(Long bookId, String addTag) {
		Book thisBook = bookRepo.findOne(bookId);
		if (tagRepo.findByName(addTag) != null) {
			Tag tag = tagRepo.findByName(addTag);
			tag.addBook(thisBook);
			return thisBook;

		}

		Tag tag = new Tag(addTag);
		tagRepo.save(tag);
		tag.addBook(thisBook);
		tagRepo.save(tag);

		return thisBook;
	}

	@RequestMapping("/del-tag")
	public Book DeleteTagFromThisBook(Long bookId, Long tagId) {
		Book thisBook = bookRepo.findOne(bookId);
		Tag thisTag = tagRepo.findOne(tagId);
		thisTag.delBook(thisBook);
		tagRepo.save(thisTag);

		return thisBook;
	}

}
