package org.wecancodeit.bookreviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Genre {

	@Id
	@GeneratedValue
	private long id;
	private String name;

	@OneToMany(mappedBy = "genre")
	private Collection<Book> books;

	public Collection<Book> getBooks() {
		return books;
	}

	public Genre(String name) {
		this.name = name;
	}

	public Genre() {

	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return id == ((Genre) obj).id;
	}
}
