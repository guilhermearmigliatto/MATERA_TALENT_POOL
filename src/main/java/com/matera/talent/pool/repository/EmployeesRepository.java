package com.matera.talent.pool.repository;

import com.matera.talent.pool.domain.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * This interface contains methods to retrieving and saving information about the Employee in the database
 *
 */
public interface EmployeesRepository extends JpaRepository<Employee, Long> {
	// Empty
}
