package cars;

import exceptions.InvalidBooking;
import exceptions.InvalidRefreshments;
import utilities.DateTime;

public class SilverServiceCar extends Car {
	private String[] refreshments;
	private double bookingFee;
	private boolean available;
	

	public SilverServiceCar(String regNo, String make, String model, String driverName, int passengerCapacity, 
			double bookingFee, String[] refreshments) throws InvalidRefreshments {
		super(regNo,make,model,driverName,passengerCapacity);
		this.bookingFee = bookingFee;
		this.refreshments = refreshments;
		available = true;
		
		//Booking Fee has to be greater than or equal to $3.00
		if (bookingFee < 3.00) {
			bookingFee = 3.00;
		}
		
		//Throw InvalidRefreshments exception found 
		if (checkRepeating(refreshments)) {
			throw new InvalidRefreshments("Invalid Refreshments Exception");
		}
		
	}
	
	@Override
	public boolean book(String firstName, String lastName, DateTime required, int numPassengers) throws InvalidBooking {
		boolean booked = false;
		DateTime currentDate = new DateTime();
		
		//Check if date is less than or equal to 3 days in future
		//Parent booking method checks for valid date
		if (DateTime.diffDays(required, currentDate) <= 3) {
			booked = super.book(firstName, lastName, required, numPassengers);
			return booked;
		}
		
		return booked;
	}
	
	@Override
	public String getDetails() {
		int count = refreshments.length;
		
		String details = super.getDetails()
			+ String.format("%s\n", "")
			+ String.format("%s\n","Refreshments Available");
		for (int i = 0; i < count; i++) {
			details = details + String.format("%-25s %s\n", "Item " + (i+1),refreshments[i]);
		}
		
		return details;
		
	}
	
	@Override
	public String toString() {
		int count = refreshments.length;
		
		String toString = super.toString();
		for (int i = 0; i < count; i++) {
			toString = toString + String.format(":" + "Item " + (i+1) + " " + refreshments[i]);
		}
		
		toString = toString + String.format(super.addBookings());
		
		return toString;
	}
	
	@Override
	public double getBookingFee() {
		return bookingFee;
	}
	
	@Override
	public boolean checkSilverServiceCar() {
		return true;
	}
	
	public boolean checkRepeating(String[] refreshments) {
		for (int i = 0; i < refreshments.length; i++) {
			for (int j = i + 1; j < refreshments.length; j++) {
				if (refreshments[i] != null && refreshments[i].equals(refreshments[j])) {
					return true;
				}
			}
		}

		return false;
	}
}
