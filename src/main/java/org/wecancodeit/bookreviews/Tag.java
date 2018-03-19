package org.wecancodeit.bookreviews;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tag {

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

	private Tag() {
	}

	public Tag(String name, Book... books) {
		this.name = name;
		this.books = new HashSet<>(asList(books));
	}

	public Collection<Book> getBooks() {
		return books;
	}

	// collections
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

		return id == ((Tag) obj).id;
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public void delBook(Book book) {
		books.remove(book);

	}

}
