package com.rama.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rama.entity.Student;
import com.rama.exception.StudentNotFoundException;
import com.rama.service.StudentServiceImpl;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

	@MockBean
	private StudentServiceImpl studentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@DisplayName("JUnit test for Add Student method")
	@Test
	void testAddStudent() throws Exception {
		//when(studentService.saveEmployee(ArgumentMatchers.any());
		Student std=new Student(101, "koti", "ramakoti@gmail.com", LocalDate.of(2010, 10, 05), 15);
		
		mockMvc.perform(post("/student/save").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(std)))
		.andExpect(status().isOk())
		.andDo(print());		
	}
	@DisplayName("JUnit test for Find StudentById method")
	@Test
	void testFindstudentById() throws Exception {
		Integer id=1;
		Student std=new Student(id, "koti", "ramakoti@gmail.com", LocalDate.of(2010, 10, 05), 15);
		when(studentService.findStudent(id)).thenReturn(std);
		mockMvc.perform(get("/student/find/{sId}", id)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(id))
		.andExpect(jsonPath("$.name").value(std.getName()))
		.andExpect(jsonPath("$.email").value(std.getEmail()))
		.andExpect(jsonPath("$.dob").value((std.getDob()).toString()))
		.andDo(print());

	}
	 @Test
	  void testReturnNotFoundStudent() throws Exception {
	    Integer id=1;
	    when(studentService.findStudent(id)).thenThrow(StudentNotFoundException.class);
	    mockMvc.perform(get("/student/find/{sId}", id))
	         .andExpect(status().isBadRequest())
	         .andDo(print());
	  }

}
