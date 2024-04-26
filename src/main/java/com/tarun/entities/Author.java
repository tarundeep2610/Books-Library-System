package com.tarun.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Author {

	@Id
	@GeneratedValue(generator = "author_seq")
	@SequenceGenerator(name = "author_seq", initialValue = 11111, allocationSize = 1)
	private int authorId;
	private String name;
	private String biography;
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	@Override
	public String toString() {
		return "id: "+this.getAuthorId()+"\n"+"name: "+this.getName()+" \n Bio: "+this.getBiography();
	}
	
}
