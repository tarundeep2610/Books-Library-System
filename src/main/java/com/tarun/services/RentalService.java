package com.tarun.services;

import com.tarun.entities.Rental;

public interface RentalService {
	public Rental addRental(Rental rental);
	public Rental getRental(int rentalId);
	public void deleteRental(int rentalId);
	public Rental updateRental(int rentalId, Rental rental);
}
