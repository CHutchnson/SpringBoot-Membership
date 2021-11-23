package com.qa.project.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.qa.project.member.*;
import org.modelmapper.ModelMapper;
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
	
	private ModelMapper mapper;
	
    public MemberService(MemberRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    
    //method to convert entity to an entityDTO 
    private MemberDTO mapToDTO(GymMember member) {
    	//iterates through the fields of the source entity and extracts the data 
        return this.mapper.map(member, MemberDTO.class);
    }
	
	public MemberDTO addMember(GymMember member) {
		//use save method to right entry into repo and read it back out
		GymMember saved = this.repo.save(member);
		return this.mapToDTO(saved);
		//return the DTO instead of the entity (note for self)
	}
	
	public List<MemberDTO> getAllMembers(){
		//get method returns the entire list of members 
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		//Entity to DTO conversion
	}
	
	public MemberDTO updateMember(long id, GymMember newMember) {
		//fetch member from database using findById method
        Optional<GymMember> existingOptional = this.repo.findById(id);
        GymMember existing = existingOptional.get();
        //update the member with new data
        existing.setEmail(newMember.getEmail());
        existing.setFirstName(newMember.getFirstName());
        //save the member back to the database 
		GymMember updated = this.repo.save(existing);
		return this.mapToDTO(updated);
		//Entity to DTO conversion 
	}
	
	/*no need for conversion because this is deleting a member so it isn't 
	*transferring any data */
	public boolean removeMember(Long id) {
        // Remove the member via id 
        this.repo.deleteById(id);
        //check if the members been remove
        boolean exists = this.repo.existsById(id);
        //returns true if the member does not exist
		return !exists;
    }
	
}





