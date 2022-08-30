package com.rama.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rama.entity.StudentErrorType;
import com.rama.exception.StudentNotFoundException;

@RestControllerAdvice
public class StudentExceptionsHandler {

	@ExceptionHandler(StudentNotFoundException.class)

	public ResponseEntity<StudentErrorType> handleStudentNotFoundEx(StudentNotFoundException exception) {

		return new ResponseEntity<StudentErrorType>(
				new StudentErrorType(exception.getMessage(), "NO_STUDENT_FOUND", "STUDENT"), HttpStatus.BAD_REQUEST);
	}
}
