package com.matera.talent.pool;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import com.matera.talent.pool.domain.Employee;
import com.matera.talent.pool.domain.Employee.EmployeeBuilder;
import com.matera.talent.pool.domain.Employee.Status;
import com.matera.talent.pool.resources.EmployeesResources;
import com.matera.talent.pool.services.EmployeesService;
import com.matera.talent.pool.services.exceptions.EmployeeNotFoundException;
import com.matera.talent.pool.utils.RestUtils;

/**
 * Tests {@link EmployeesResources} for successful and non-successful responses of URI's
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesResources.class)
public class EmployeeRestControllerIntegrationTest {
 
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private EmployeesService employeesService;
    
    /**
     * Creating some rules before run the test
     */
    @Before
    public void setUp() {
    	
    	// Creating list of Employees to be returned by /employees URI
		EmployeeBuilder builder = new EmployeeBuilder();
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
				.status(Status.ACTIVE)
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
	    Mockito.when(employeesService.list()).thenReturn(allEmployees);
	    
	    // Creating a single Employee to be returned by /employees/id URI
		Employee singleEmployee = builder
				.id(1l)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(LocalDate.of(1990, 12, 31))
				.dateOfEmployment(LocalDate.of(2019, 04, 02))
				.status(Status.ACTIVE)
				.build();
		Mockito.when(employeesService.search(1l)).thenReturn(singleEmployee);
		
	    // Creating one Employee to update and another to be returned after update.
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
		Mockito.when(employeesService.update(toUpdateEmployee, 1l)).thenReturn(updatedEmployee);
		
		// Throw EmployeeNotFoundException when search the id 22
		Mockito.when(employeesService.search(22l)).thenThrow(EmployeeNotFoundException.class);
		
		
	    // Creating a Employee and another to be returned.
		Employee toCreateEmployee = builder
				.id(null)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(null)
				.dateOfEmployment(null)
				.status(Status.ACTIVE)
				.build();
		Employee newEmployeeCreated = builder
				.id(1l)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(null)
				.dateOfEmployment(null)
				.status(Status.ACTIVE)
				.build();
		Mockito.when(employeesService.create(toCreateEmployee)).thenReturn(newEmployeeCreated);
    }
    
    /**
     * Testing the method get to retrieve all Employees.
     */
    @Test
    public void getAllEmployeesThenReturnJsonArray() throws Exception {

        mvc.perform( MockMvcRequestBuilders
        	      .get("/employees")
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isOk())
        		  .andExpect(jsonPath("$", Matchers.hasSize(3)))
        		  .andExpect(jsonPath("$[0].id", Matchers.is(1)))
        		  .andExpect(jsonPath("$[0].firstName", Matchers.is("Alan")))
        		  .andExpect(jsonPath("$[0].middleInitial", Matchers.is("Smith")))
        		  .andExpect(jsonPath("$[0].lastName", Matchers.is("Carter")))
        		  .andExpect(jsonPath("$[0].dateOfBirth", Matchers.is("1990-12-31")))
        		  .andExpect(jsonPath("$[0].dateOfEmployment", Matchers.is("2019-04-02")))
        		  .andExpect(jsonPath("$[0].status", Matchers.is("ACTIVE")))
        		  .andExpect(jsonPath("$[1].id", Matchers.is(2)))
        		  .andExpect(jsonPath("$[2].id", Matchers.is(3)));
    }
    
    /**
     * Testing the method get to retrieve only one Employee by id.
     */
    @Test
    public void getEmployeeByIdThenReturnJson() throws Exception {

        mvc.perform( MockMvcRequestBuilders
        	      .get("/employees/{id}", 1l)
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isOk())
        		  .andExpect(jsonPath("$.id", Matchers.is(1)))
        		  .andExpect(jsonPath("$.firstName", Matchers.is("Alan")))
        		  .andExpect(jsonPath("$.middleInitial", Matchers.is("Smith")))
        		  .andExpect(jsonPath("$.lastName", Matchers.is("Carter")))
        		  .andExpect(jsonPath("$.dateOfBirth", Matchers.is("1990-12-31")))
        		  .andExpect(jsonPath("$.dateOfEmployment", Matchers.is("2019-04-02")))
        		  .andExpect(jsonPath("$.status", Matchers.is("ACTIVE")));
    }
    
    /**
     * Testing the method get when the Employee doesn't exist.
     */
    @Test
    public void getNotFoundEmployeeById() throws Exception {
         
    	mvc.perform(MockMvcRequestBuilders.get("/employees/{id}", 22L)
      	      .accept(MediaType.APPLICATION_JSON))
      	      .andDo(print())
              .andExpect(status().isNotFound());
    }
    
    /**
     * Testing the method post to create one Employee.
     */
    @Test
    public void createEmployee() throws Exception {
    	
    	EmployeeBuilder builder = new EmployeeBuilder();
		Employee singleEmployee = builder
				.id(null)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(null)
				.dateOfEmployment(null)
				.status(Status.ACTIVE)
				.build();

    	mvc.perform(MockMvcRequestBuilders.post("/employees/")
    			  .content(RestUtils.asJsonString(singleEmployee))
    			  .contentType(MediaType.APPLICATION_JSON)
    			  .accept(MediaType.APPLICATION_JSON))
    			  .andDo(print())
                  .andExpect(status().isCreated());
    }
    
    /**
     * Testing the method delete when the user doesn't has authorization.
     */
    @Test
    public void unauthorizedToRemoveEmployeeById() throws Exception {

        mvc.perform( MockMvcRequestBuilders
        	      .delete("/employees/{id}", 1l)
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isUnauthorized());
    }
    
    /**
     * Testing the method delete to remove by id.
     */
    @Test
    public void removeEmployeeById() throws Exception {

        mvc.perform( MockMvcRequestBuilders
        	      .delete("/employees/{id}", 1l)
        	      .header("Authorization", "Basic bWF0ZXJhOm1hdGVyYQ==")
        	      .accept(MediaType.APPLICATION_JSON))
        	      .andDo(print())
        	      .andExpect(status().isNoContent());
        
    }
    
    /**
     * Testing the method put to update an Employee.
     */
    @Test
    public void updateEmployee() throws Exception {
    	
    	EmployeeBuilder builder = new EmployeeBuilder();
		Employee singleEmployee = builder
				.id(null)
				.firstName("Alan")
				.middleInitial("Smith")
				.lastName("Carter")
				.dateOfBirth(null)
				.dateOfEmployment(null)
				.status(Status.ACTIVE)
				.build();

    	mvc.perform(MockMvcRequestBuilders.put("/employees/{id}", 1l)
    			  .content(RestUtils.asJsonString(singleEmployee))
    			  .contentType(MediaType.APPLICATION_JSON)
    			  .accept(MediaType.APPLICATION_JSON))
    			  .andDo(print())
                  .andExpect(status().isNoContent());
    }
}

