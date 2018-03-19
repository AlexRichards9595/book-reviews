package org.wecancodeit.bookreviews;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	List<Author> findByName(String name);
}
