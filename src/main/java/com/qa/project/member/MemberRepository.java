package com.qa.project.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*By extending JPARepository this instance will inherit all of the basic 
CRUD functionality.*/
@Repository
public interface MemberRepository extends JpaRepository<GymMember, Long> {
	
	//Custom Query method that allows us to find members by other variables
	public List<GymMember> findByAge(int age);
	public List<GymMember> findByFirstName(String firstName);
	public List<GymMember> findByLastName(String lastName);
	public List<GymMember> findByEmail(String email);
	
}
