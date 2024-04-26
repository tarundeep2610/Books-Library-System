package com.tarun.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tarun.entities.Author;
import com.tarun.services.AuthorService;

@RestController
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	@PostMapping("/create")
	public ResponseEntity<Author> createAuthor(@RequestBody Author author){
		Author savedAuthor = authorService.addAuthor(author);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Author> getAuthor(@RequestParam int authorId){
		Author author = authorService.getAuthor(authorId);
		if(author==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(author);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Author> deleteAuthor(@RequestParam int authorId){
		Author author = authorService.getAuthor(authorId);
		if(author==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(author);
		}
		
		authorService.deleteAuthor(authorId);
		return ResponseEntity.status(HttpStatus.OK).body(author);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Author> updateAuthor(@RequestParam int authorId, @RequestBody Author author){
		Author temp = authorService.getAuthor(authorId);
		if(temp==null) {
			Author newAuthor = authorService.addAuthor(author);
			return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
		}
		
		temp = authorService.updateAuthor(authorId, author);
		return ResponseEntity.status(HttpStatus.OK).body(temp);
	}
}
