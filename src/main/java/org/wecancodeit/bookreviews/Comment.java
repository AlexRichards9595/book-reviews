package org.wecancodeit.bookreviews;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	private Comment() {
	}

	public Comment(String name,  Book...books) {
		this.name = name;
		this.books = new HashSet<>(asList(books));
	}
	
	
	public Collection<Book> getBooks() {
		return books;
	}


	@ManyToMany
	private Collection<Book> books;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return id == ((Comment) obj).id;
	}

}
