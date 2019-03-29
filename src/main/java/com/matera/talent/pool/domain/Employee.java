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
 */
@Entity
public class Employee {

	public enum Status {
		ACTIVE,
		INACTIVE;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long id;

	@NotEmpty(message = "The firstName of employee can not be empty.")
	private String firstName;

	@JsonInclude(Include.NON_NULL)
	private String middleInitial;

	@JsonInclude(Include.NON_NULL)
	private String lastName;

	// generates "yyyy-MM-dd" output
	@JsonInclude(Include.NON_NULL)
	private LocalDate dateOfBirth;

	// generates "yyyy-MM-dd" output
	@JsonInclude(Include.NON_NULL)
	private LocalDate dateOfEmployment;
	
	@JsonInclude(Include.NON_NULL)
	private Status status;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfEmployment() {
		return dateOfEmployment;
	}

	public void setDateOfEmployment(LocalDate dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
