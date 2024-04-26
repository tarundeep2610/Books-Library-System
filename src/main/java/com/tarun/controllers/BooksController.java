package com.tarun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tarun.entities.Book;
import com.tarun.services.BookService;

@RestController
@RequestMapping("/book")
public class BooksController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/create")
	public ResponseEntity<Book> createbook(@RequestBody Book book){
		System.out.println(book);
		Book savedBook = bookService.addBook(book);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Book> getbook(@RequestParam int bookId){
		Book book = bookService.getBook(bookId);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Book> deletebook(@RequestParam int bookId){
		Book book = bookService.getBook(bookId);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		bookService.deleteBook(bookId);
		return ResponseEntity.status(HttpStatus.OK).body(book);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Book> updatebook(@RequestParam int bookId, @RequestBody Book book){
		Book temp = bookService.getBook(bookId);
		if(temp==null) {
			Book newBook = bookService.addBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
		}
		
		temp = bookService.updateBook(bookId, book);
		return ResponseEntity.status(HttpStatus.OK).body(temp);
	}

}
