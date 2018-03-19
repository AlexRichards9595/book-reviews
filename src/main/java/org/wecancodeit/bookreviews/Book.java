package org.wecancodeit.bookreviews;

import static java.util.Arrays.asList;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Author author;

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

	public Author getAuthor() {
		return author;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "books")
	private Collection<Tag> tags;

	public Collection<Tag> getTags() {
		return tags;

	}

	@JsonIgnore
	@ManyToMany(mappedBy = "books")
	private Collection<Comment> comments;

	public Collection<Comment> getComments() {
		return comments;
	}

	// constructors
	@SuppressWarnings("unused")
	private Book() {
	}

	public Book(String title, Author author, String imageUrl, String description) {
		this.title = title;
		this.author = author;
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
