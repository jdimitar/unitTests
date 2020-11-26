package com.example.unitTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.example.unitTest.controller.UserController;
import com.example.unitTest.entity.User;
import com.example.unitTest.repository.AddressRepository;
import com.example.unitTest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javassist.ClassPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserMvcTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	AddressRepository addressRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	
	@Test
	void whenValidInput_returns200() throws Exception{
		
		User user = new User();
		user.setId(1L);
		user.setName("Dimitar");
		
		
		
		
		mockMvc.perform(post("/update/{userId}", 1L)
				.contentType("application/json")
//				.param("street", "Partizanska")
//				.param("city", "Skopje")
//				.param("zip", "1000")
				.content(objectMapper.writeValueAsString("{\r\n"
						+ "    \"street\":\"Partizanska\",\r\n"
						+ "    \"city\":\"Skopje\",\r\n"
						+ "    \"zip\":\"1000\"\r\n"
						+ "}")))
				.andExpect(status().isOk());
				
	}
	
	@Test
	void receive_Hellо() throws Exception{
		mockMvc.perform(get("/hello"))
		.andExpect((ResultMatcher) containsString("hello"));
		
	}
	
	

}
