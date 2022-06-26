package com.qa.project.controllers;


import org.junit.jupiter.api.Test;

import static org.mockito.BDDMockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.project.member.*;
import com.qa.project.service.MemberService;

@SpringBootTest//(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MemberControllerUnitTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	@MockBean
	private MemberService service;
	
	@Test
	void testcreate() throws Exception {
		GymMember testMem = new GymMember(1L, "001", "Roy", "Jones", 18, "RoyJones@Gmail.com");
	    	
		 when(this.service.addMember(testMem)).thenReturn((testMem));
	    	
	     this.mock
	         .perform(post("/create")
	             .accept(MediaType.APPLICATION_JSON)
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(this.jsonifier.writeValueAsString(testMem)))
	         .andExpect(status().isCreated())
	         .andExpect(content().json(this.jsonifier.writeValueAsString((testMem))));
	    }
	
	@Test
	void testgetAll() throws Exception {
		GymMember testMem = new GymMember(1L, "001", "Roy", "Jones", 18, "RoyJones@Gmail.com");
		 List<GymMember> result = new ArrayList<>();
		 result.add(testMem);
	    	
		 when(this.service.getAllMembers()).thenReturn((result));
	    	
	     this.mock
	         .perform(get("/getAll")
	             .accept(MediaType.APPLICATION_JSON)
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(this.jsonifier.writeValueAsString(result)))
	         .andExpect(status().isOk())
	         .andExpect(content().json(this.jsonifier.writeValueAsString((result))));
	    }
	
	@Test
	void testgetById() throws Exception {
		GymMember testMem = new GymMember(1L, "001", "Roy", "Jones", 18, "RoyJones@Gmail.com");
	    	
		 when(this.service.getById(1L)).thenReturn((testMem));
	    	
	     this.mock
	         .perform(get("/getByID/1")
	             .accept(MediaType.APPLICATION_JSON)
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(this.jsonifier.writeValueAsString(testMem)))
	         .andExpect(status().isOk())
	         .andExpect(content().json(this.jsonifier.writeValueAsString((testMem))));
	    }
	
	@Test
	void testupdate() throws Exception {
		GymMember testMem = new GymMember(1L, "001", "Roy", "Jones", 18, "RoyJones@Gmail.com");
	    	
		 when(this.service.updateMember(1L, testMem)).thenReturn((testMem));
	    	
	     this.mock
	         .perform(put("/update/1")
	             .accept(MediaType.APPLICATION_JSON)
	             .contentType(MediaType.APPLICATION_JSON)
	             .content(this.jsonifier.writeValueAsString(testMem)))
	         .andExpect(status().isAccepted())
	         .andExpect(content().json(this.jsonifier.writeValueAsString((testMem))));
	    }
	
	@Test
	void testdelete() throws Exception {
		
		when(this.service.removeMember(1L)).thenReturn(true);
	    	
	     this.mock
	         .perform(delete("/delete/1")
	             .accept(MediaType.APPLICATION_JSON)
	             .contentType(MediaType.APPLICATION_JSON))
	         .andExpect(status().isGone());
	    }
	

}
