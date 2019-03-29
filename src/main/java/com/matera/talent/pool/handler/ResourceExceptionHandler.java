package com.matera.talent.pool.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.matera.talent.pool.domain.ErrorDetails;
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
	public ResponseEntity<ErrorDetails> handleLivroNaoEncontradoException
							(EmployeeNotFoundException e, HttpServletRequest request) {
		
		ErrorDetails error = new ErrorDetails();
		error.setStatus(404l);
		error.setMessage("The employee was not found.");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
