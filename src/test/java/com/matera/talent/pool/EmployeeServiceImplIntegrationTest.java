package com.matera.talent.pool;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.matera.talent.pool.domain.Employee;
import com.matera.talent.pool.domain.Employee.EmployeeBuilder;
import com.matera.talent.pool.domain.Employee.Status;
import com.matera.talent.pool.repository.EmployeesRepository;
import com.matera.talent.pool.services.EmployeesService;

/**
 * This class test some methods of {@link EmployeesService}
 */
@RunWith(SpringRunner.class)
public class EmployeeServiceImplIntegrationTest {
 
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public EmployeesService employeeService() {
            return new EmployeeServiceImpl();
        }
    }
 
    @Autowired
    private EmployeesService employeeService;
 
    @MockBean
    private EmployeesRepository employeeRepository;
 
    /**
     * Creating some rules before run the test
     */
    @Before
    public void setUp() {

    	// Creating one single employee to return by id.
    	EmployeeBuilder builder = new EmployeeBuilder();
		Employee singleEmployee = builder
				.id(1l)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();
	    Mockito.when(employeeRepository.findById(1l)).thenReturn(Optional.of(singleEmployee));

	    // Creating a list with 3 employees to return by finding all.
		Employee employee1 = builder
				.id(1l)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();
		Employee employee2 = builder
				.id(2l)
				.firstName("Katie")
				.middleInitial("Hall")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1980, 10, 10))
				.dateOfEmployment(LocalDate.of(2017, 12, 20))
				// Adding an INACTIVE employee
				.status(Status.INACTIVE)
				.build();
		Employee employee3 = builder
				.id(3l)
				.firstName("Mary")
				.middleInitial("Smith")
				.lastName("Evans")
				.dateOfBirth(LocalDate.of(1979, 05, 10))
				.dateOfEmployment(LocalDate.of(2018, 01, 01))
				.status(Status.ACTIVE)
				.build();
		List<Employee> allEmployees = Arrays.asList(employee1, employee2, employee3);
	    Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
	    
	    // Creating a employee with id null to create a new one.
		Employee newEmployee = builder
				.id(null)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();
		Employee createEmployee = builder
				.id(1l)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();
	    Mockito.when(employeeRepository.save(newEmployee)).thenReturn(createEmployee);
	    
	    // Creating one employee to remove and another one to be returned after remove.
		Employee toRemoveEmployee = builder
				.id(1l)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.INACTIVE)
				.build();
		Employee removedEmployee = builder
				.id(1l)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.INACTIVE)
				.build();
	    Mockito.when(employeeRepository.save(toRemoveEmployee)).thenReturn(removedEmployee);
	    
	    // Creating one employee to update and another to be returned after update.
		Employee toUpdateEmployee = builder
				.id(1l)
				.firstName("Bernard")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();
		Employee updatedEmployee = builder
				.id(1l)
				.firstName("Arnold")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();
	    Mockito.when(employeeRepository.save(toUpdateEmployee)).thenReturn(updatedEmployee);
	    
    }
    
    /**
     * Testing the method EmployeeService.search(Long id)
     */
    @Test
    public void searchByIdThenValidateByFirstName() {
        Employee found = employeeService.search(1l);
        assertEquals(found.getFirstName(), "Alan");
     }
    
    /**
     * Testing the method EmployeeService.list()
     */
    @Test
    public void searchAllEmployeesThenValidateByFirstName() {
        List<Employee> found = employeeService.list();

        // Returning only 2 employee because one is INACTIVE
        assertEquals(found.size(), 2);
        assertEquals(found.get(0).getFirstName(), "Alan");
        assertEquals(found.get(1).getFirstName(), "Mary");
     }
    
    /**
     * Testing the method EmployeeService.create(Employee employee)
     */
    @Test
    public void createNewEmployeeThenValidateId() {
    	EmployeeBuilder builder = new EmployeeBuilder();
		Employee newEmployee = builder
				.id(null)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();
    	
        Employee created = employeeService.create(newEmployee);
        assertEquals(created.getId(), (Long) 1l);
     }

    /**
     * Testing the method EmployeeService.remove(Long id)
     */
    @Test
    public void removeEmployeeThenValidateStatus() {
    	Employee removed = employeeService.remove(1l);
    	assertEquals(removed.getStatus(), Status.INACTIVE);
    }
    
    /**
     * Testing the method EmployeeService.update(Employee employee, Long id)
     */
    @Test
    public void updateEmployeeThenValidateFirstName() {
    	
    	EmployeeBuilder builder = new EmployeeBuilder();
		Employee singleEmployee = builder
				.id(1l)
				.firstName("Bernard")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();

    	Employee updated = employeeService.update(singleEmployee, 1l);
    	assertEquals(updated.getFirstName(), "Arnold");
    }
}

