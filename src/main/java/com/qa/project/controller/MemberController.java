package com.qa.project.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private MemberService service; //imported from the service class
	
	@Autowired
	public MemberController(MemberService service) {
		this.service = service; //service method
	}
	
	List<GymMember> members = new ArrayList<>(); //empty array list of members
	
	//data is sent to the server from the user to create a membership 
	 @PostMapping("/create")
	    public GymMember addMember(@RequestBody GymMember member) {
	        return this.service.addMember(member);
	    } //add the newly created member to the members list and return it
	
	//maps request to the get all members method to get/read the data 
	 @GetMapping("/getAll")
	    public List<GymMember> getAllMembers() {
			return this.service.getAllMembers();
	        // generate an array of all members in the body of the HTTP response.
	    }
	 
	 //maps request to get a member by their ID and read the data
	 @GetMapping("/getByID/{id}")
	 public GymMember readById(@PathVariable long id) {
		 return this.service.readById(id);
		 //generate the specific members ID 
	 }
	 @GetMapping("/getByAge/{age}")
	 public List<GymMember> readByAge(@PathVariable int age){
		 return this.service.getByAge(age);
	 }//example generate all members aged 18 
	 
	 @GetMapping("/getByFirstName/{firstName}")
	 public List<GymMember> readByFirstName(@PathVariable String firstName){
		 return this.service.getByFirstName(firstName);
	 }//example generate all members with the first name Roy
	 
	 @GetMapping("/getByLastName/{lastName}")
	 public List<GymMember> readByLastName(@PathVariable String lastName){
		 return this.service.getByLastName(lastName);
	 }//example generate all members with the last name James

	 @GetMapping("/getByEmail/{email}")
	 public List<GymMember> readByEmail(@PathVariable String email){
		 return this.service.getByEmail(email);
	 }
	 
	 //request is sent to the server to update the data of a specific member
	 @PutMapping("/update/{id}")
	    public GymMember updateMember(@PathVariable ("id")int id, @RequestBody GymMember member) {
	        return this.service.updateMember(id, member);
	        //Updating the members list (adding and removing members)
	 }
	    
	 
	 //request is sent to the server to delete the data
	 @DeleteMapping("/delete/{id}") 
	 public boolean deleteMember(@PathVariable long id) {
		//deletes via unique id 
		 return this.service.removeMember(id);
		 //returns the deleted member from list
	 }
		 /*note for self explaining path variable
		 Extracts values from the URL the request was sent to.
		 If the value in {} matches the parameter name then the value will be inserted
		 automatically, otherwise, the parameter can be specified.
		 */ 
	 
	 
	  @GetMapping("/test")
	    public String test() {
	        return "Im still working!";
	    }
	 
}
