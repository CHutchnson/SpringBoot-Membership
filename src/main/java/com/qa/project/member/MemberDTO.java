package com.qa.project.member;

import java.time.LocalDate;

/*Note to self
 *Allows the ability to combine data from multiple tables together
 *Allows the ability to send complex data over a single request
 *Prevents entity from being exposed directly to the user 
 * 
 */
public class MemberDTO {
	
	private long id;
	
	private String accountNumber;
	
	private String firstName;

	private String lastName;
	
	private LocalDate DOB;
	
	private String email;
	
	public MemberDTO() {
		super();
	}
	
}
