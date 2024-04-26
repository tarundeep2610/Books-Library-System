package com.tarun.services;

import com.tarun.entities.Author;

public interface AuthorService {

	public Author addAuthor(Author author);
	public Author getAuthor(int authorId);
	public void deleteAuthor(int authorId);
	public Author updateAuthor(int authorId, Author author);
}
