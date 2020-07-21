package com.registration.exception;

public class InvalidRegistrationDetails extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRegistrationDetails(String s) {
		super(s);
	}
	
}