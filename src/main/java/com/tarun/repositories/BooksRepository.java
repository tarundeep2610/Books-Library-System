package com.tarun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarun.entities.Book;


public interface BooksRepository extends JpaRepository<Book, Integer>{

}
