package com.qa.project.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/*By extending JPARepository this instance will inherit all of the basic 
CRUD functionality.*/
@Repository
public interface MemberRepository extends JpaRepository<GymMember, Long> {
	
	//Custom Query method that allows us to find members by their last name
	public List<GymMember> findByLastName(String lastName);
	
}
