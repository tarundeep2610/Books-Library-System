package com.tarun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarun.entities.Book;
import com.tarun.entities.Rental;
import com.tarun.repositories.RentalRepository;
import com.tarun.services.BookService;
import com.tarun.services.RentalService;

@Service
public class RentalServiceImpl implements RentalService{

	@Autowired
	RentalRepository rentalRepository;
	
	@Autowired
	BookService bookService;

	@Override
	public Rental addRental(Rental rental) {
		return rentalRepository.save(rental);
	}

	@Override
	public Rental getRental(int rentalId) {
		return rentalRepository.findById(rentalId).orElse(null);
	}

	@Override
	public void deleteRental(int rentalId) {
		rentalRepository.deleteById(rentalId);
	}

	@Override
	public Rental updateRental(int rentalId, Rental rental) {
		Rental old = getRental(rentalId);
		
		if( rental.getRenterName()!=null && !rental.getRenterName().equals("") && !old.getRenterName().equals(rental.getRenterName()) ) {
			old.setRenterName(rental.getRenterName());
		}
		
		System.out.println(rental);
		
		if(rental.getBook()!=null) {
			Book a = bookService.getBook(rental.getBook().getBookid());
			
			if(a!=null && (old.getBook()== null || rental.getBook().getBookid()!=old.getBook().getBookid()) && a.isAvailable() ) {
				old.getBook().setIsAvailable(true);
				a.setIsAvailable(false);
				old.setBook(a);
			}
		}
		
		if(rental.getReturnDate()!=null && (old.getReturnDate()==null || old.getReturnDate()!=rental.getReturnDate())) {
			old.setReturnDate(rental.getReturnDate());
		}
		if(rental.getRentalDate()!=null && (old.getRentalDate()==null || old.getRentalDate()!=rental.getRentalDate())) {
			old.setRentalDate(rental.getRentalDate());
		}

		
		return rentalRepository.save(old);
	}

}
