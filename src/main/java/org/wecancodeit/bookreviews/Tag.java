package org.wecancodeit.bookreviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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

	public Tag(String name) {
		this.name = name;

	}

	@ManyToMany(mappedBy = "tags")
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

		return id == ((Tag) obj).id;
	}

}
