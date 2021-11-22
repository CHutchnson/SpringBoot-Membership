package com.qa.project.service;


import java.util.ArrayList;
import java.util.List;
import com.qa.project.member.GymMember;
import org.springframework.stereotype.Service;


/*moving CRUD functionality to this class instead
 * Service layer defines an applications set of operations 
 * Good practice to have business logic here 
 */
@Service
public class MemberService {
	
	private List<GymMember> members = new ArrayList<>(); //empty members list
	
	public GymMember addMember(GymMember member) {
		//Adding a new member
		this.members.add(member);
		//returning the last added member from the list 
		return this.members.get(this.members.size() - 1);
	}
	
	public List<GymMember> getAllMembers(){
		//get method returns the entire list of members 
		return this.members;
	}
	
	public GymMember updateMember(int id, GymMember member) {
		// Remove existing member with matching 'id'
        this.members.remove(id);
        // Add new member in its place
        this.members.add(id, member);
        // Return updated member from List
        return this.members.get(id);
	}
	
	public GymMember removeMember(int id) {
        // Remove the member via id and return the removed member
        return this.members.remove(id);
    }
	
}





