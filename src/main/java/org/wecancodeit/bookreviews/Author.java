package org.wecancodeit.bookreviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Author {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	private String imageUrl;

	public long getId() {
		return id;
	}

	private Author() {
	}

	public Author(String name, String description, String imageUrl) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;

	}

	public Object getName() {
		return name;
	}
	@JsonIgnore
	@OneToMany(mappedBy = "author")
	private Collection<Book> books;

	public Collection<Book> getBooks() {
		return books;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return id == ((Author) obj).id;
	}

}
