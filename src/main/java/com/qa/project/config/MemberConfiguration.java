package com.qa.project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*a simple way to convert from the entity to the DTO
 * 
 */

@Configuration
public class MemberConfiguration {
	@Bean
	 public ModelMapper mapper() {
	 	return new ModelMapper();
	}
}
