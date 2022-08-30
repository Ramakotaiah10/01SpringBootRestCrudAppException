package com.rama.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rama.entity.Student;
import com.rama.exception.StudentEmailExitException;
import com.rama.exception.StudentNotFoundException;
import com.rama.repo.StudentEnityRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
    private StudentEnityRepository repo;
    
	public void saveEmployee(Student student)
	{
		Optional<Student> studentOptional = repo.findByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalArgumentException("Email Alread Taken"); 
		}
		repo.save(student);		
	}
	
	public void deleteStudent(Integer studentId) 
	{
		boolean existsById = repo.existsById(studentId);
		if (!existsById) {
			throw new IllegalArgumentException(
                 "Student with id "+studentId+" does not exists");
		}
		repo.deleteById(studentId);
	}
	
	public List<Student> getStudents() {
		return repo.findAll();
	}
	@Transactional
	public void updateStudent(Integer studetId, String sname, String semail) {
		
		Student student=repo.findById(studetId).orElseThrow(()-> 
		new StudentNotFoundException(
				" Student with id "+studetId+" does not exist")
		);
		
		if(sname!=null && sname.length()>0 && !Objects.equals(student.getName(), sname))
		{  
			student.setName(sname);
			
		}
				
		if(semail!=null && semail.length()>0 && !Objects.equals(student.getEmail(), semail))
		{
			Optional<Student> studentOptional = repo.findByEmail(semail);
			if (studentOptional.isPresent()) {
					throw new StudentEmailExitException("Email Already Taken"); 
			}else {
			student.setEmail(semail);
	      }
	    }	
    }

	@Override
	public Student findStudent(Integer id) {
		
		Student findById = repo.findById(id).orElseThrow(()->
	         new StudentNotFoundException(" Student with id "+id+" does not exist"));
		
	   return findById;
	}

	
}

