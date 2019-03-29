package com.matera.talent.pool.services.exceptions;

/**
 * 
 * This Exception is thrown when an Employee is not found or is INACTIVE
 *
 */
public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5364761458696718237L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
	public EmployeeNotFoundException(String message, Throwable error) {
		super(message, error);
	}
}
