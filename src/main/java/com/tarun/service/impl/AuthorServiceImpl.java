package com.tarun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarun.entities.Author;
import com.tarun.repositories.AuthorRepository;
import com.tarun.services.AuthorService;
@Service
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public Author addAuthor(Author author) {
		Author savedAuthor = authorRepository.save(author);
		return savedAuthor;
	}

	@Override
	public Author getAuthor(int authorId) {
		Author author = authorRepository.findById(authorId).orElse(null);
		return author;
	}

	@Override
	public void deleteAuthor(int authorId) {
		authorRepository.deleteById(authorId);
	}

	@Override
	public Author updateAuthor(int authorId, Author author) {
		Author old = getAuthor(authorId);
		
		if( author.getName()!=null && !author.getName().equals("") && !old.getName().equals(author.getName()) ) {
			old.setName(author.getName());
		}
		
		if( author.getBiography()!=null && !old.getBiography().equals(author.getBiography()) && !author.getBiography().equals("") ) {
			old.setBiography(author.getBiography());
		}
		
		return authorRepository.save(old);
	}
}
