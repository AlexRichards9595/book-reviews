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

	public Collection<Tag> getTags() {
		return tags;
	}

	// collections
	@ManyToMany
	private Collection<Tag> tags;

	// constructors
	private Book() {
	}

	public Book(String title, Author author, String imageUrl, String description, Tag... tags) {
		this.title = title;
		this.tags = new HashSet<>(asList(tags));
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

	public void addTag(Tag tag) {
		System.out.println(tags);
		System.out.println(tag);
		tags.add(tag);
	}
}
