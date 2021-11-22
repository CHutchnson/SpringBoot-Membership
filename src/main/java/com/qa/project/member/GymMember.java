package com.qa.project.member;

import javax.persistence.GenerationType;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class GymMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, nullable = false)
	private String accountNumber;
	
	@Column
	private String firstName;

	@Column
	private String lastName;
	
	@Column
	private LocalDate DOB;
	
	@Column
	private String email;

	
	
	
	
}
