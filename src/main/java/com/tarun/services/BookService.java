package com.tarun.services;

import com.tarun.entities.Book;

public interface BookService {
	public Book addBook(Book book);
	public Book getBook(int bookId);
	public void deleteBook(int bookId);
	public Book updateBook(int bookId, Book book);
}
