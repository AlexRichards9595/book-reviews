package org.wecancodeit.bookreviews;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author {
	
	@Id
	@GeneratedValue
	private long id; 
	private String name;

	public long getId() {
		return id;
	}
	
	private Author () {
	}
	
	public Author(String name) {
		this.name = name;
		
	}
	public Object getName() {
		return name;
	}


}
