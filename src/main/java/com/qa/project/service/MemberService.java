package com.qa.project.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.qa.project.member.GymMember;
import com.qa.project.member.MemberRepository;

import org.springframework.stereotype.Service;


/*moving CRUD functionality to this class instead
 * Service layer defines an applications set of operations 
 * Good practice to have business logic here 
 */
@Service
public class MemberService {
	
	private List<GymMember> members = new ArrayList<>(); //empty members list
	
	private MemberRepository repo;
	//Adding repo dependency to our service 
    public MemberService(MemberRepository repo) {
        super();
        this.repo = repo;
    }
	
	public GymMember addMember(GymMember member) {
		//use save method to right entry into repo and read it back out
		return this.repo.save(member);
		//same thing as before with less lines
	}
	
	public List<GymMember> getAllMembers(){
		//get method returns the entire list of members 
		return this.repo.findAll();
	}
	
	public GymMember updateMember(long id, GymMember newMember) {
		//fetch member from database using findById method
        Optional<GymMember> existingOptional = this.repo.findById(id);
        GymMember existing = existingOptional.get();
        //update the member with new data
        existing.setEmail(newMember.getEmail());
        existing.setFirstName(newMember.getFirstName());
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





