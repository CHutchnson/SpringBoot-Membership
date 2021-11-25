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
import java.util.List;
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
	void testGetByAge() {
		new GymMember(1, null, null, null, 18, null);
		List<GymMember> memberlist = new ArrayList<GymMember>();	
		when(repo.findByAge(18)).thenReturn(memberlist);
		assertThat(service.getByAge(18)).isEqualTo(memberlist);
		verify(repo, times(1)).findByAge(18);
	}
	
    @Test
    void testGetByFirstName(){
    	new GymMember(1, "Roy", null, null, 0, null);
    	List<GymMember> memberlist = new ArrayList<GymMember>();
    	when(repo.findByFirstName((String) "Roy")).thenReturn(memberlist);
    	assertThat(service.getByFirstName("Roy")).isEqualTo(memberlist);
    	verify(repo, times(1)).findByFirstName("Roy");
    }
        
    @Test
    void testGetByLastName(){
    	new GymMember(1, "Jones", null, null, 0, null);
    	List<GymMember> memberlist = new ArrayList<GymMember>();
    	when(repo.findByLastName((String) "Jones")).thenReturn(memberlist);
    	assertThat(service.getByLastName("Jones")).isEqualTo(memberlist);
    	verify(repo, times(1)).findByLastName("Jones");
    }
	    
    @Test
    void testGetByEmail(){
    	new GymMember(1, "RoyJones@Gmail.com", null, null, 0, null);
    	List<GymMember> memberlist = new ArrayList<GymMember>();
    	when(repo.findByEmail((String) "RoyJones@Gmail.com")).thenReturn(memberlist);
    	assertThat(service.getByEmail("RoyJones@Gmail.com")).isEqualTo(memberlist);
    	verify(repo, times(1)).findByEmail("RoyJones@Gmail.com");
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
    	GymMember testMem = new GymMember(1, null, null, null, 0, null);
    	when(repo.existsById((long) 1)).thenReturn(false, true);
    	assertThat(service.updateMember((long )1, testMem)).isEqualTo(true);
    	
    	testMem.setId(1);
    	verify(repo, times(1)).deleteById((long) 1);
    	verify(repo, times(2)).existsById((long) 1);
    	verify(repo, times(1)).save(testMem);	
    }
    
}
