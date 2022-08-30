package com.rama.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rama.entity.Student;

public interface StudentEnityRepository extends JpaRepository<Student, Integer> {

	
	public Optional<Student> findByEmail(String email); 
}
