package com.rama.rest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rama.entity.Student;
import com.rama.service.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentServiceImpl service;

	@PostMapping("/save")
	public void addStudent(@RequestBody Student student)
	{
		service.saveEmployee(student);
	}
	
	@GetMapping()
	public ResponseEntity<List<Student>> getStudents()
    {
		List<Student> students = service.getStudents();
		
		return new ResponseEntity<List<Student>>(students,HttpStatus.OK);
	}
	@GetMapping("/find/{sId}")
	public ResponseEntity<Student> searchStudent(
			         @PathVariable("sId") Integer id)
	{
		Student findStudent = service.findStudent(id);	
		
		return new ResponseEntity<Student>(findStudent,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{sId}")
	public ResponseEntity<String> deleteStudent(
			         @PathVariable("sId") Integer id)
	{
		service.deleteStudent(id);	
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PutMapping("/update/{sId}")
	public void updateStudent(@PathVariable("sId") Integer id,
			@RequestParam String name,@RequestParam String email)
	{
		service.updateStudent(id, name, email);
		
		return ;
	}
}
