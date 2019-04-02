package com.matera.talent.pool.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matera.talent.pool.domain.Employee;
import com.matera.talent.pool.domain.Employee.EmployeeBuilder;
import com.matera.talent.pool.domain.Employee.Status;
import com.matera.talent.pool.repository.EmployeesRepository;
import com.matera.talent.pool.services.exceptions.EmployeeNotFoundException;

/**
 * 
 * This class provides operations for the save and retrieve employees
 *
 */
@Service
public class EmployeesService {

	@Autowired
	protected EmployeesRepository employeesRepository;
	
	/**
	 * List all employees who are not INACTIVE
	 * @return List<Employee> - list of active employees
	 */
	public List<Employee> list() {
		List<Employee> employees = employeesRepository.findAll();
		// Searching only ACTIVE employees
		List<Employee> activeEmployees = employees.stream()
				.filter(e -> e.getStatus() != Status.INACTIVE)
				.collect(Collectors.toList());
		return activeEmployees;
	}
	
	/**
	 * Create new Employee and put ACTIVE status
	 * @param employee - The new Employee to be created
	 * @return Employee - The new Employee created 
	 */
	public Employee create(Employee employee) {
		
		EmployeeBuilder builder = new EmployeeBuilder(employee);
		Employee newEmployee = builder
				.id(null)
				.status(Status.ACTIVE)
				.build();

		newEmployee = employeesRepository.save(newEmployee);
		return newEmployee;
	}
	
	/**
	 * Search an Employee by id
	 * @param id - The identifier of the Employee to be searched for
	 * @return employee - The Employee found
	 * @throws EmployeeNotFoundException - Throws exception if employee does not exist or is INACTIVE.
	 */
	public Employee search(Long id) {
		Employee employee = findActiveEmployee(id);
		return employee;
	}

	/**
	 * Remove an Employee by id
	 * @param id - The identifier of the Employee to be removed
	 * @return 
	 * @throws EmployeeNotFoundException - Throws exception if employee does not exist or is INACTIVE.
	 */
	public Employee remove(Long id) {
		Employee employee = findActiveEmployee(id);
		
		EmployeeBuilder builder = new EmployeeBuilder(employee);
		Employee newEmployee = builder
				.status(Status.INACTIVE)
				.build();

		 Employee removed = employeesRepository.save(newEmployee);
		 return removed;
	}
	
	/**
	 * Update an Employee by id
	 * @param employee - New informations of Employee
	 * @param id - The identifier of the Employee to be updated
	 * @return 
	 */
	public Employee update(Employee employee, Long id) {
		
		// Check if employee exist
		findActiveEmployee(id);
		
		EmployeeBuilder builder = new EmployeeBuilder(employee);
		Employee newEmployee = builder
				.id(id)
				.status(Status.ACTIVE)
				.build();

		return employeesRepository.save(newEmployee);
	}
	
	/**
	 * Check if employee exist and is not INACTIVE
	 * @param employee - The employee to check
	 * @return Employee - Return the employee if It exists and the status is not INACTIVE.
	 * @throws EmployeeNotFoundException - Throws exception if employee does not exist or is INACTIVE.
	 */
	private Employee findActiveEmployee(Long id) throws EmployeeNotFoundException {
		Employee employee = employeesRepository.findById(id).orElse(null);
		if  (employee == null || employee.getStatus() == Status.INACTIVE) {
			throw new EmployeeNotFoundException("The employee was not found.");
		}
		return employee;
	}
}
