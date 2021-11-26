package com.qa.project.member;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*By extending JPARepository this instance will inherit all of the basic 
CRUD functionality.*/
@Repository
public interface MemberRepository extends JpaRepository<GymMember, Long> {
	
}
