package org.wecancodeit.bookreviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Author {
	
	@Id
	@GeneratedValue
	private long id; 
	private String name;

	public long getId() {
		return id;
	}
	
	private Author() {
	}
	
	public Author(String name) {
		this.name = name;
		
	}
	public Object getName() {
		return name;
	}
	
	@ManyToMany(mappedBy = "authors")
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
