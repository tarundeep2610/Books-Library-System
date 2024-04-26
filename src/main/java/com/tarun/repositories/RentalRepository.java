package com.tarun.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tarun.entities.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
