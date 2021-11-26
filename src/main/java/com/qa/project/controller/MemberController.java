package com.qa.project.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	    public ResponseEntity<GymMember> addMember(@RequestBody GymMember member) {
	        return new ResponseEntity<>(this.service.addMember(member), HttpStatus.CREATED);
	    } //add the newly created member to the members list and return it
	
	//maps request to the get all members method to get/read the data 
	 @GetMapping("/getAll")
	    public ResponseEntity<List<GymMember>> getAllMembers() {
			return new ResponseEntity<>(this.service.getAllMembers(), HttpStatus.OK);
	        // generate an array of all members in the body of the HTTP response.
	    }
	 
	 //maps request to get a member by their ID and read the data
	 @GetMapping("/getByID/{id}")
	 public ResponseEntity<GymMember> readById(@PathVariable long id) {
		 return new ResponseEntity<> (this.service.getById(id), HttpStatus.OK); 
	 }

	 
	 //request is sent to the server to update the data of a specific member
	 @PutMapping("/update/{id}")
	    public ResponseEntity<GymMember> updateMember(@PathVariable ("id")long id, @RequestBody GymMember member) {
		
			 return new ResponseEntity<>(service.updateMember(id, member), HttpStatus.ACCEPTED);
		 }
	        
	        //Updating the members list (adding and removing members)
	 
	    
	 
	 //request is sent to the server to delete the data
	 @DeleteMapping("/delete/{id}") 
	 public ResponseEntity<String> deleteMember(@PathVariable long id) {
		//deletes via unique id 
		 return new ResponseEntity<>("Member Deleted", HttpStatus.GONE);
		 //returns the deleted member from list
	 }
		 /*note for self explaining path variable
		 Extracts values from the URL the request was sent to.
		 If the value in {} matches the parameter name then the value will be inserted
		 automatically, otherwise, the parameter can be specified.
		 */ 
	 
}
