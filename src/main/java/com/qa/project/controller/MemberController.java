package com.qa.project.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.qa.project.member.*;
import com.qa.project.service.MemberService;

@RestController
public class MemberController {
	
	private MemberService service; //imported from our service class
	
	public MemberController(MemberService service) {
		this.service = service; //service method
	}
	
	List<GymMember> members = new ArrayList<>(); //empty array list of members
	
	//data is sent to the server from the user to create a membership 
	 @PostMapping("/create")
	    public GymMember addMember(@RequestBody GymMember member) {
	        return this.service.addMember(member);
	    } //add the newly created member to the membership list and return it
	
	//maps request to the get all members method to get/read the data 
	 @GetMapping("/getAll")
	    public List<GymMember> getAllMembers() {
			return this.service.getAllMembers();
	        // generate an array of memberships in the body of the HTTP response.
	    }	//right now our membership list is empty 
	 
	 //request is sent to the server to update the data 
	 @PutMapping("/update")
	    public GymMember updateMember(@PathParam ("id")int id, @RequestBody GymMember member) {
	        return this.service.updateMember(id, member);
	        /*Updating the members list (adding and removing members)
	        *with less lines of code by calling on the service class 
	        *with the this.service.createdmethod that was made
	        */
	        
	        /*note for self explaining path parameter
	        PathParam is a parameter annotation which allows you to map query 
	        parameters in the request to parameters in the method.*/
	    }
	 
	 //request is sent to the server to delete the data
	 @DeleteMapping("/delete/{id}") 
	 public boolean deleteMember(@PathVariable long id) {
		//deletes via unique id 
		 return this.service.removeMember(id);
		 //returns the deleted member from list
		 
		 /*note for self explaining path variable
		 Extracts values from the URL the request was sent to.
		 If the value in {} matches the parameter name then the value will be inserted
		 automatically, otherwise, the parameter can be specified.
		 */
		 
	 }
	 
	  @GetMapping("/test")
	    public String test() {
	        return "Im still working!";
	    }
	 
}
