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
		//use save member to repo and read member back out
		this.repo.saveAndFlush(member);
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
	
	
	public GymMember updateMember(long id, GymMember newMember) {
		 Optional<GymMember> existingOptional = this.repo.findById(id);
	        GymMember existing = existingOptional.get();
	        //update the member with new data
	        existing.setEmail(newMember.getEmail());
	        existing.setFirst_name(newMember.getFirst_name());
	        existing.setLast_name(newMember.getLast_name());
	        existing.setAge(newMember.getAge());;
	        //save the member back to the database 
			return this.repo.save(existing);
	}
	
	public boolean removeMember(Long id) {
        // Remove the member via id 
        this.repo.deleteById(id);
        //check if the members been remove
        return !repo.existsById(id);
    }
	
}





