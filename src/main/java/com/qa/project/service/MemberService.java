package com.qa.project.service;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.qa.project.member.GymMember;
import com.qa.project.member.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*moving CRUD functionality to this class instead
 * Service layer defines an applications set of operations 
 * Good practice to have business logic here 
 */
@Service
public class MemberService {

	private MemberRepository repo;
	//Adding repo dependency to our service 
	
	@Autowired
    public MemberService(MemberRepository repo) {
        super();
        this.repo = repo;
    }
	
	public GymMember addMember(GymMember member) {
		//use save member to repo and read member back out
		this.repo.save(member);
		return (member);
	}
	
	public List<GymMember> getAllMembers(){
		//get method returns the entire list of members 
		return this.repo.findAll();
	}
	
	public GymMember getById(long id) {
		return repo.findById(id).orElseThrow(EntityNotFoundException::new);
		/* note to self
		 * if the id is present it will return the member 
		 * custom exception instance if wrong id it will throw a not found exception
		 */
	}
	
	public List<GymMember> getByAge(int age) {
		return this.repo.findByAge(age);
		//using the custom method
	}
	
	public List<GymMember> getByFirstName(String firstName) {
		return this.repo.findByFirstName(firstName);
		//using the custom method
	}
	
	public List<GymMember> getByLastName(String lastName) {
		return this.repo.findByLastName(lastName);
		//using the custom method
	}
	
	public List<GymMember> getByEmail(String email) {
		return this.repo.findByEmail(email);
		//using the custom method
	}
	
	
	public boolean updateMember(long id, GymMember newMember) {
		if(removeMember(id)) {
			newMember.setId(id);
			addMember(newMember);
			return repo.existsById(id);
		}
		return false;
	}
	
	public boolean removeMember(Long id) {
        // Remove the member via id 
        this.repo.deleteById(id);
        //check if the members been remove
        return !repo.existsById(id);
    }
	
}





