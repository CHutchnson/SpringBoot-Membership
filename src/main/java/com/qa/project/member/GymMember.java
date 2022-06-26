package com.qa.project.member;

import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GymMember {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false) 
	private String account_number;
	
	@Column
	private String first_name;

	@Column
	private String last_name;
	
	@Column  
	private int age;
	
	@Column
	private String email;
	
	public GymMember(String account_number, String first_name, String last_name, int age, String email) {
		super();
		this.account_number = account_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.email = email;
	}
	
}
