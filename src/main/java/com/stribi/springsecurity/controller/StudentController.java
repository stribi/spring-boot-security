package com.stribi.springsecurity.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stribi.springsecurity.domain.Student;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
	
	private static final List<Student> STUDENTS = Arrays.asList(
				new Student(1L, "Stribor Popovic"),
				new Student(2L, "Stribor Popovic"),
				new Student(3L, "Stribor Popovic"),
				new Student(4L, "Stribor Popovic")			
			);
	
	@GetMapping("/{studentId}")
	public Student getStudent(@PathVariable Long studentId) {
		
		return STUDENTS.stream().filter(student -> (studentId.equals(student.getId()))).findFirst().orElseThrow(() -> new IllegalArgumentException("Student with id: " + studentId + " does not exists"));
		
	}

}
