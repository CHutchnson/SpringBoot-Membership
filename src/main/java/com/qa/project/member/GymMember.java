package com.qa.project.member;

import javax.persistence.GenerationType;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class GymMember {
	
	//variables that each GymMember will have 
	
	//This is a strategy to automatically generate an id primary key
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false) //must be unique can't be empty
	private String accountNumber;
	
	@Column
	private String firstName;

	@Column
	private String lastName;
	
	@Column
	private int age;
	
	@Column
	private String email;
	
	public GymMember() {
		super();
	}
	
	public GymMember(long id, String accountNumber, String firstName, String lastName, int age, String email) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
	}

	//getters and setters 
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public int getage() {
		return age;
	}

	public void setage(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	///equals method needed for testing to compare the object values
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GymMember other = (GymMember) obj;
		return Objects.equals(age, other.age) && Objects.equals(accountNumber, other.accountNumber)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName);
	}

	//string representation of the GymMember 
	@Override
	public String toString() {
		return "GymMember [id=" + id + ", accountNumber=" + accountNumber + ", firstName=" + firstName + ", lastName="
				+ lastName + ", age=" + age + ", email=" + email + "]";
	}

	
}
