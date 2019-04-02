package com.matera.talent.pool.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class represent an Employee Entity
 * Using Builder Pattern to make this class immutable
 */
@Entity
public final class Employee {
	
	public enum Status {
		ACTIVE,
		INACTIVE;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private final Long id;

	@NotEmpty(message = "The firstName of employee can not be empty.")
	private final String firstName;

	@JsonInclude(Include.NON_NULL)
	private final String middleInitial;

	@JsonInclude(Include.NON_NULL)
	private final String lastName;

	// generates "yyyy-MM-dd" output
	@JsonInclude(Include.NON_NULL)
	private final LocalDate dateOfBirth;

	// generates "yyyy-MM-dd" output
	@JsonInclude(Include.NON_NULL)
	private final LocalDate dateOfEmployment;
	
	@JsonInclude(Include.NON_NULL)
	private final Status status;
	
	private Employee(EmployeeBuilder builder) {
		this.id = builder.id;
		this.firstName = builder.firstName;
		this.middleInitial = builder.middleInitial;
		this.lastName = builder.lastName;
		this.dateOfBirth = builder.dateOfBirth;
		this.dateOfEmployment = builder.dateOfEmployment;
		this.status = builder.status;
	}
	
	// Default constructor for entity used by hibernate
	private Employee() {
		this.id = null;
		this.firstName = null;
		this.middleInitial = null;
		this.lastName = null;
		this.dateOfBirth = null;
		this.dateOfEmployment = null;
		this.status = null;
	}
	
	public static class EmployeeBuilder {
		private Long id;
		private String firstName;
		private String middleInitial;
		private String lastName;
		private LocalDate dateOfBirth;
		private LocalDate dateOfEmployment;
		private Status status;

		public EmployeeBuilder() {
			// Empty
		}

		public EmployeeBuilder(Employee employee) {
			this.id = employee.id;
			this.firstName = employee.firstName;
			this.middleInitial = employee.middleInitial;
			this.lastName = employee.lastName;
			this.dateOfBirth = employee.dateOfBirth;
			this.dateOfEmployment = employee.dateOfEmployment;
			this.status = employee.status;
		}

		public EmployeeBuilder id(Long id) {
			this.id = id;
			return this;
		}
		
		public EmployeeBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public EmployeeBuilder middleInitial(String middleInitial) {
			this.middleInitial = middleInitial;
			return this;
		}
		
		public EmployeeBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public EmployeeBuilder dateOfBirth(LocalDate dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}
		
		public EmployeeBuilder dateOfEmployment(LocalDate dateOfEmployment) {
			this.dateOfEmployment = dateOfEmployment;
			return this;
		}
		
		public EmployeeBuilder status(Status status) {
			this.status = status;
			return this;
		}

		public Employee build() {
			return new Employee(this);
		}
	}
	
	// Getters of Employee
	
	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public LocalDate getDateOfEmployment() {
		return dateOfEmployment;
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((dateOfEmployment == null) ? 0 : dateOfEmployment.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleInitial == null) ? 0 : middleInitial.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (dateOfEmployment == null) {
			if (other.dateOfEmployment != null)
				return false;
		} else if (!dateOfEmployment.equals(other.dateOfEmployment))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleInitial == null) {
			if (other.middleInitial != null)
				return false;
		} else if (!middleInitial.equals(other.middleInitial))
			return false;
		if (status != other.status)
			return false;
		return true;
	}
}
