package com.matera.talent.pool.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matera.talent.pool.domain.Employee;
import com.matera.talent.pool.services.EmployeesService;

/**
 * REST controller for managing Employees
 */
@RestController
@RequestMapping("/employees")
public class EmployeesResources {

	@Autowired
	EmployeesService employeesService;

	/**
	 * Get a list of all ACTIVE Employees
	 * Example: http://localhost:8080/employees
	 * 
	 * @return A list of Employees 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> list() {
		List<Employee> employees = employeesService.list();
		return ResponseEntity.status(HttpStatus.OK).body(employees);
	}
	
	/**
	 * Get a specific ACTIVE Employee by id
	 * Example: http://localhost:8080/employees/1
	 * 
	 * @return A single Employee 
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> search(@PathVariable("id") Long id) {
		Employee employee = employeesService.search(id);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}

	/**
	 * Create a new Employee
	 * 
	 * @param employee - The new Employee to be created
	 * @return The URI of the new Employee created
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody Employee employee) {
		employee = employeesService.create(employee);

		// Generating the URI of new employee created.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(employee.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	/**
	 * Remove a specific Employee by id
	 * This operation needs a Basic Authentication.
	 * The user and password is matera/matera
	 * Needs to put in Header the Key "Authorization" and Value "Basic bWF0ZXJhOm1hdGVyYQ=="
	 * 
	 * @param id - The identifier of the Employee to be removed 
	 * @return No Content
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("id") Long id) {
		employeesService.remove(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Update a Employee by id
	 * 
	 * @param employee - The new Employee to be updated
	 * @return No content
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Employee newEmployee,
			@PathVariable("id") Long id) {
		employeesService.update(newEmployee, id);
		return ResponseEntity.noContent().build();
	}
}
