package org.wecancodeit.bookreviews;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Book {

	// local fields and variables
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;
	private String imageUrl;

	@ManyToOne
	private Genre genre;

	// getters
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Genre getGenre() {
		return genre;
	}

	public Collection<Author> getAuthors() {
		return authors;
	}

	// collections
	@ManyToMany
	private Collection<Author> authors;

	// constructors
	private Book() {
	}

	public Book(String title) {
		this.title = title;
	}

	public Book(String title, Genre genre) {
		this.title = title;
		this.genre = genre;
	}

	public Book(String title, Author... authors) {
		this.title = title;
		this.authors = new HashSet<>(asList(authors));
	}

	public Book(String title, Genre genre, String imageUrl, String description, Author... authors) {
		this.title = title;
		this.authors = new HashSet<>(asList(authors));
		this.genre = genre;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return id == ((Book) obj).id;
	}
}
