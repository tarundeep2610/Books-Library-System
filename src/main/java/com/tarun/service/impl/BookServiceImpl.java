package com.tarun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarun.entities.Author;
import com.tarun.entities.Book;
import com.tarun.repositories.BooksRepository;
import com.tarun.services.AuthorService;
import com.tarun.services.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BooksRepository booksRepository;
	
	@Autowired
	AuthorService authorService;

	@Override
	public Book addBook(Book book) {
		return booksRepository.save(book);
	}

	@Override
	public Book getBook(int bookId) {
		return booksRepository.findById(bookId).orElse(null);
	}

	@Override
	public void deleteBook(int bookId) {
		booksRepository.deleteById(bookId);
	}

	@Override
	public Book updateBook(int bookId, Book book) {
		Book old = getBook(bookId);
		
		if( book.getTitle()!=null && !book.getTitle().equals("") && !old.getTitle().equals(book.getTitle()) ) {
			old.setTitle(book.getTitle());
		}
		
		if( old.getPubYear()!=book.getPubYear() ) {
			old.setPubYear(book.getPubYear());
		}
		
		if( old.isAvailable() != book.isAvailable()) {
			old.setIsAvailable(book.isAvailable());
		}
		
		if( book.getIsbn()!=null && !book.getIsbn().equals("") && !old.getIsbn().equals(book.getIsbn()) ) {
			old.setIsbn(book.getIsbn());
		}
		
		if(book.getAuthor()!=null) {
			Author a = authorService.getAuthor(book.getAuthor().getAuthorId());
			
			if(a!=null && (old.getAuthor()== null || book.getAuthor().getAuthorId()!=old.getAuthor().getAuthorId()) ) {
				old.setAuthor(a);
			}
		}
				
		return booksRepository.save(old);
	}

}
