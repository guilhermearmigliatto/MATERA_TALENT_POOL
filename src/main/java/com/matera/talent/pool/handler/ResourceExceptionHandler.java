package com.matera.talent.pool.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matera.talent.pool.domain.ErrorDetails;
import com.matera.talent.pool.domain.ErrorDetails.ErrorDetailsBuilder;
import com.matera.talent.pool.services.exceptions.EmployeeNotFoundException;

/**
 * 
 * This class has methods to specifically handle exceptions thrown by request handling (@RequestMapping)
 *
 */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	/**
	 * Convert a EmployeeNotFoundException to a ErrorDetails object
	 * 
	 * @param e - The EmployeeNotFoundException
	 * @param request - The HttpServletRequest
	 * @return - HttpStatus.NOT_FOUND with a ErrorDetails
	 */
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException
							(EmployeeNotFoundException e, HttpServletRequest request) {
		
		ErrorDetailsBuilder builder = new ErrorDetailsBuilder();
		ErrorDetails error = builder.status(404l)
				.message("The employee was not found.")
				.timestamp(System.currentTimeMillis())
				.build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
