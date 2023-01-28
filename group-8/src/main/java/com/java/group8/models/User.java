package com.java.group8.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "First Name is required")
	@Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters.")
	private String firstName;
	
	@NotEmpty(message = "Last Name is required.")
	@Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters.")
	private String lastName;
	
	@NotEmpty(message = "Address is required")
	private String address;
	
	@NotEmpty(message = "City is required")
	private String city;
	
	@NotEmpty(message = "Zip code is required.")
	@Size(min =5, max = 5, message = "Zip code must be 5 digits.")
	private String zipcode;	
	
	@NotEmpty(message = "State is required")
	private String state;

	@NotEmpty(message = "Email address is required.")
	@Email(message = "Please enter a valid email address.")
	private String email;
	
	@Column(nullable = false)
	@Size(min = 8, max = 128, message="Password is required and must be at least 8 characters.")
	private String password;
	
	@Transient
	@Size(min = 8, max = 128, message = "Password must be at least 8 characters.")
	private String confirm;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

}
