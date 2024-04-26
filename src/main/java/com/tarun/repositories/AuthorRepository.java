package com.tarun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarun.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
