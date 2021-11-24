package com.qa.project.service;


import java.util.List;
import java.util.Optional;

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
		//use save method to right entry into repo and read it back out
		return this.repo.save(member);
	}
	
	public List<GymMember> getAllMembers(){
		//get method returns the entire list of members 
		return this.repo.findAll();
	}
	
	public GymMember readById(long id) {
		GymMember found = repo.findById(id).orElseThrow(EntityNotFoundException::new);
		return found;
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
	
	
	public GymMember updateMember(long id, GymMember newMember) {
		//fetch member from database using findById method
        Optional<GymMember> existingOptional = this.repo.findById(id);
        GymMember existing = existingOptional.get();
        //update the member with new data
        existing.setEmail(newMember.getEmail());
        existing.setFirstName(newMember.getFirstName());
        existing.setLastName(newMember.getLastName());
        existing.setAge(newMember.getAge());;
        //save the member back to the database 
		return this.repo.save(existing);
	}
	
	public boolean removeMember(Long id) {
        // Remove the member via id 
        this.repo.deleteById(id);
        //check if the members been remove
        boolean exists = this.repo.existsById(id);
        //returns true if the member does not exist
		return !exists;
    }
	
}





