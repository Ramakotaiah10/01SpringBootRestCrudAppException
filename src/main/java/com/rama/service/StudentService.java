package com.rama.service;
import java.util.List;

import com.rama.entity.Student;

public interface StudentService {

	public void saveEmployee(Student student);
	List<Student> getStudents();
	public void deleteStudent(Integer studentId);
	public void updateStudent(Integer studetId, String sname, String semail);
	Student findStudent(Integer id);

	}
