package com.tarun.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Rental {

	@Id
	@GeneratedValue(generator = "rental_seq")
	@SequenceGenerator(name = "rental_seq", initialValue = 11111, allocationSize = 1)
	private int rentalId;
	@OneToOne
	private Book book;
	
	private String renterName;
	private Date rentalDate;
	private Date returnDate;
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getRenterName() {
		return renterName;
	}
	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	@Override
	public String toString() {
		return "Rental [rentalId=" + rentalId + ", book=" + book + ", renterName=" + renterName + ", rentalDate="
				+ rentalDate + ", returnDate=" + returnDate + "]";
	}
	
	
}
