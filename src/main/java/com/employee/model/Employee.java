package com.employee.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "first name should not be null")
	@Size(min = 1, max = 50, message = " first name size should between 1 to 50 range")
	private String firstName;

	@NotNull(message = "lastName should not be null")
	@Size(min = 1, max = 50, message = " first name size should between 1 to 50 range")
	private String lastName;

	@NotNull(message = "email should not be null")
	@Email(message = "enter valid email")
	private String email;

	@ElementCollection
	@Column(name = "phone_number")
	@NotEmpty(message = "phone num should not be empty")
	private List<@Pattern(regexp = "[0-9]{10}$", message = "Enter valid phone num phone number with 10 digits") String> phoneNumbers;

	@NotNull(message = "d should not be null")
	//@Temporal(TemporalType.DATE)
	private LocalDate doj;

	@NotNull(message = "lastName should not be null")
	@Min(value = 0)
	private Double salary;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Employee(Long id,
			@NotNull(message = "first name should not be null") @Size(min = 1, max = 50, message = " first name size should between 1 to 50 range") String firstName,
			@NotNull(message = "lastName should not be null") @Size(min = 1, max = 50, message = " first name size should between 1 to 50 range") String lastName,
			@NotNull(message = "email should not be null") @Email(message = "enter valid email") String email,
			@NotEmpty(message = "phone num should not be empty") List<@Pattern(regexp = "[0-9]{10}$", message = "Enter valid phone num phone number with 10 digits") String> phoneNumbers,
			@NotNull(message = "d should not be null") LocalDate doj,
			@NotNull(message = "lastName should not be null") @Min(0) Double salary) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumbers = phoneNumbers;
		this.doj = doj;
		this.salary = salary;
	}

	public Employee() {
	};

}
