package com.qa.project.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.project.member.*;
import com.qa.project.service.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest
public class MembersServiceTest {
	
	 @Autowired
	    private MemberService service;

	    @MockBean
	    private MemberRepository repo;

	    @Test
	    void testCreate() {
	    	GymMember testMem = new GymMember();
	    	assertThat(service.addMember(testMem)).isEqualTo(testMem);
	    	verify(repo, times(1)).save(testMem);
	    }
	    
	    @Test
	    void testGetAll() {
	    	when(repo.findAll()).thenReturn(new ArrayList<>());
	    	assertThat(service.getAllMembers()).isEqualTo(new ArrayList<>());
	    	verify(repo, times(1)).findAll();
	    }
	    
	    @Test
	    void testGetById(){
	    	GymMember testMem = new GymMember(1, null, null, null, 0, null);
	    	Optional<GymMember> option = Optional.of(testMem);
	    	when(repo.findById((long) 1)).thenReturn(option);
	    	assertThat(service.getById(1)).isEqualTo(testMem);
	    	verify(repo, times(1)).findById((long) 1);
	    }

    
    @Test
    void DeleteById() {
    	when(repo.existsById((long)1)).thenReturn(false, true);
    	assertThat(service.removeMember((long) 1));
    	verify(repo, times(1)).deleteById((long) 1);
    	verify(repo, times(1)).existsById((long) 1);
    }
	    
    @Test
    void testUpdate() {
    	GymMember testMem = new GymMember(1, "100", "Leo", "James", 20, "LeoJames@mail.com");
    	System.out.println("Before: " + testMem);
    	Optional<GymMember> option = Optional.of(testMem); //should be saved to the repo
    	when(repo.findById((long) 1)).thenReturn(option); //find existing member by ID )
    	when(service.updateMember(1, testMem)).thenReturn(testMem); //when we call the update method return test member
    	assertThat(service.updateMember((long )1, testMem)).isEqualTo(testMem); //see if it returns said member
    	testMem.setAge(21);; //update the test members age 
    	assertThat(service.updateMember((long )1, testMem)).isEqualTo(testMem); //see if it returns same member with new age
    	verify(repo, times(2)).save(testMem); //should be saved to the repo again with updated changes
    	System.out.println("After: " + testMem);
    }
    
}
