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
	private String accountNumber;
	
	@Column
	private String firstName;

	@Column
	private String lastName;
	
	@Column  
	private int age;
	
	@Column
	private String email;
	
}
