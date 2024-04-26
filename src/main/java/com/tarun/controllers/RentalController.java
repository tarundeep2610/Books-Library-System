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
import com.tarun.entities.Rental;
import com.tarun.services.BookService;
import com.tarun.services.RentalService;

@RestController
@RequestMapping("/rental")
public class RentalController {

	@Autowired
	RentalService rentalService;
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/create")
	public ResponseEntity<Rental> createrental(@RequestBody Rental rental){
		System.out.println(rental);
		if(rental.getBook()==null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		Book b = bookService.getBook(rental.getBook().getBookid());
		if(b==null || !b.isAvailable()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		b.setIsAvailable(false);
		bookService.updateBook(b.getBookid(), b);
		Rental savedRental = rentalService.addRental(rental);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Rental> getrental(@RequestParam int rentalId){
		Rental rental = rentalService.getRental(rentalId);
		if(rental==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(rental);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<Rental> deleterental(@RequestParam int rentalId){
		Rental rental = rentalService.getRental(rentalId);
		if(rental==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		rentalService.deleteRental(rentalId);
		return ResponseEntity.status(HttpStatus.OK).body(rental);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Rental> updaterental(@RequestParam int rentalId, @RequestBody Rental rental){
		Rental temp = rentalService.getRental(rentalId);
		if(temp==null) {
			Rental newRental = rentalService.addRental(rental);
			return ResponseEntity.status(HttpStatus.CREATED).body(newRental);
		}
		
		temp = rentalService.updateRental(rentalId, rental);
		return ResponseEntity.status(HttpStatus.OK).body(temp);
	}
}
