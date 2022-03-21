package com.qa.project.controllers;


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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.qa.project.member.GymMember;
import com.qa.project.member.MemberRepository;
import com.qa.project.service.MemberService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:member-schema.sql", "classpath:member-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class MembersControllerTest {

	
	@Autowired
	private MockMvc mock;
	
	@MockBean
    private MemberRepository repo;
	
	 @Autowired
	 private ObjectMapper jsonifier; //converts objects into json strings
	 
	 @Autowired
	    private MemberService service;
	 
	 private GymMember TEST_SAVED_PERSON = new GymMember(1, "001", "Roy", "Jones", 19, "RoyJones@Gmail.com");

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
		public void testGetByID() throws Exception {
			
			Optional<GymMember> option = Optional.of(TEST_SAVED_PERSON);
			given(repo.findById((long) 1)).willReturn(option);
			MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getByID/1");
		 	mockRequest.contentType(MediaType.APPLICATION_JSON);
		 	mockRequest.content(this.jsonifier.writeValueAsString(TEST_SAVED_PERSON));
		 	mockRequest.accept(MediaType.APPLICATION_JSON);
		 	
		 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		 	ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_SAVED_PERSON));
		 	
		 	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		}

		@Test
		public void testReadAll() throws Exception {
			 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/getAll");
			 	mockRequest.contentType(MediaType.APPLICATION_JSON);
			 	mockRequest.content(this.jsonifier.writeValueAsString(TEST_SAVED_PERSON));
			 	mockRequest.accept(MediaType.APPLICATION_JSON);
			 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
			 	this.mock.perform(mockRequest).andExpect(matchStatus);
		}

		 @Test
		 public void testUpdate() throws Exception {
			 Optional<GymMember> option = Optional.of(TEST_SAVED_PERSON);
			 given(repo.findById((long) 1)).willReturn(option);
			 given(service.updateMember(1, TEST_SAVED_PERSON)).willReturn(TEST_SAVED_PERSON);
			 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.PUT, "/update/1");
			 	mockRequest.contentType(MediaType.APPLICATION_JSON);
			 	TEST_SAVED_PERSON.setAge(23);
			 	mockRequest.content(this.jsonifier.writeValueAsString(TEST_SAVED_PERSON));
			 	mockRequest.accept(MediaType.APPLICATION_JSON);
			 	
			 	ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
			 	
			 	this.mock.perform(mockRequest).andExpect(matchStatus);
 }
		 
	@Test
	 public void testDelete() throws Exception {
				mock.perform(delete("/delete/1")).andExpect(status().isGone());
			}
	 
}








