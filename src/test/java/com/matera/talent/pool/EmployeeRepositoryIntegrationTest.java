package com.matera.talent.pool;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.matera.talent.pool.domain.Employee;
import com.matera.talent.pool.domain.Employee.EmployeeBuilder;
import com.matera.talent.pool.domain.Employee.Status;
import com.matera.talent.pool.repository.EmployeesRepository;

/**
 * This class test some methods of {@link EmployeesRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeesRepository employeesRepository;

    /**
     * Testing the method findById
     */
    @Test
    public void findByIdThenReturnEmployee() {

		EmployeeBuilder builder = new EmployeeBuilder();
		Employee employee = builder
				.id(null)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();

        entityManager.persist(employee);
        entityManager.flush();

        Optional<Employee> found = employeesRepository.findById(employee.getId());
        assertEquals(employee.getFirstName(), found.get().getFirstName());
    }
    
    /**
     * Testing the method findAll
     */
    @Test
    public void findAllThenReturnListOfEmployees() {
        List<Employee> foundall = employeesRepository.findAll();
        assertEquals(10, foundall.size());
    }
    
    /**
     * Testing the method save
     */
    @Test
    public void saveThanValidate() {
    	
		EmployeeBuilder builder = new EmployeeBuilder();
		Employee employee = builder
				.id(null)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();

        entityManager.persist(employee);
        entityManager.flush();
        
        Long newId = employee.getId();
		Employee newEmployee = builder
				.id(newId)
				.firstName("Bob")
				.status(Status.ACTIVE)
				.build();
    	
    	Employee savedEmployee = employeesRepository.save(newEmployee);
    	
    	assertEquals(savedEmployee.getId(), newId);
    	assertEquals(savedEmployee.getFirstName(), "Bob");
    }
 
}
