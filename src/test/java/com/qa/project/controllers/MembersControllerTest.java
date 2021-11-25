package com.qa.project.controllers;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.project.member.GymMember;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:member-schema.sql", "classpath:member-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class MembersControllerTest {

	
	@Autowired
	private MockMvc mock;
	
	 @Autowired
	 private ObjectMapper jsonifier; //converts objects into json strings
	 
	 private final GymMember Test_Saved_Member = new GymMember(4, "004", "Tory", "Lanes", 29, "ToryLanes@Gmail.com");

	 @Test
	 public void testCreate() throws Exception {
		 GymMember testMem = new GymMember(1, "001", "Roy", "Jones", 18, "RoyJones@Gmail.com");
		 
	 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/create");
	 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	 	mockRequest.content(this.jsonifier.writeValueAsString(testMem));
	 	mockRequest.accept(MediaType.APPLICATION_JSON);
	 	
	 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
	 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testMem));
	 	
	 	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);	
	 }
	 
	 @Test
	 public void testGetAll() throws Exception {
		 List<GymMember> NewList = new ArrayList<GymMember>();
		 
	 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getAll");
	 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	 	mockRequest.content(this.jsonifier.writeValueAsString(NewList));
	 	mockRequest.accept(MediaType.APPLICATION_JSON);
	 	
	 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isFound();
	 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(NewList));
	 	
	 	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);	
	 	
	 } 
		 @Test
		 public void testGetById() throws Exception {
			 GymMember testMem = new GymMember(0, "001", "Roy", "Jones", 18, "RoyJones@Gmail.com");
	 
	 
 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/findByID/1");
 	mockRequest.contentType(MediaType.APPLICATION_JSON);
 	mockRequest.content(this.jsonifier.writeValueAsString(testMem));
 	mockRequest.accept(MediaType.APPLICATION_JSON);
 	
 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(0));
 	
 	mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

		 }
		 
	@Test
	 public void testGetByAge() throws Exception {
		final GymMember testMem = new GymMember(1, "001", "Tory", "Jones", 30, "RoyJones@Gmail.com");
	 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getByAge/30");
	 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	 	mockRequest.content(this.jsonifier.writeValueAsString(testMem));
	 	mockRequest.accept(MediaType.APPLICATION_JSON);
	 	
	 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isFound();
	 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testMem));
	 	
	 	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	 	
	 }
	 
	 @Test
	 public void testGetByFirstName() throws Exception {
		 GymMember testMem = new GymMember(1, "001", "Cory", "Jones", 18, "CoryJones@Gmail.com");
	 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getByFirstName/Cory");
	 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	 	mockRequest.content(this.jsonifier.writeValueAsString(testMem));
	 	mockRequest.accept(MediaType.APPLICATION_JSON);
	 	
	 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isFound();
	 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(Test_Saved_Member));
	 	
	 	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	 	
	 }
	 
	 @Test
	 public void testGetByLastName() throws Exception {
		 final GymMember testMem = new GymMember(1, "001", "Tory", "James", 18, "ToryJames@Gmail.com");
	 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getByLastName/James");
	 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	 	mockRequest.content(this.jsonifier.writeValueAsString(testMem));
	 	mockRequest.accept(MediaType.APPLICATION_JSON);
	 	
	 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isFound();
	 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testMem));
	 	
	 	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	 	
	 }
	 
	 @Test
	 public void testGetByEmail() throws Exception {
		 GymMember testMem = new GymMember(0, null, null, null, 0, "TrollJones@Gmail.com");
		 
	 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getByEmail/TrollJones@Gmail.com");
	 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	 	mockRequest.content(this.jsonifier.writeValueAsString(testMem));
	 	mockRequest.accept(MediaType.APPLICATION_JSON);
	 	
	 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isFound();
	 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testMem));
	 	
	 	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	 	
	 }
	 
	 @Test
	 public void testUpdate() throws Exception {
		 GymMember testMem = new GymMember(0, "001", "Tory", "Jones", 18, "new@Gmail.com");
		 
		 
	 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/update/1");
	 	mockRequest.contentType(MediaType.APPLICATION_JSON);
	 	mockRequest.content(this.jsonifier.writeValueAsString(testMem));
	 	mockRequest.accept(MediaType.APPLICATION_JSON);
	 	
	 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
	 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(testMem));
	 	
	 	mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	 	
	 }
	 
	 @Test
	 public void testDelete() throws Exception {
				mock.perform(delete("/delete/2")).andExpect(status().isGone());
			}
	 
}








