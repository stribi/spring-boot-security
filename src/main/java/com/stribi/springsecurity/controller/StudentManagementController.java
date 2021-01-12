package com.stribi.springsecurity.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stribi.springsecurity.domain.Student;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
	
	static final List<Student> STUDENTS = Arrays.asList(
			new Student(1L, "Stribor Popovic"),
			new Student(2L, "Goran Popovic"),
			new Student(3L, "Borna Srebocan"),
			new Student(4L, "Vedrana Prpic")			
		);
	
	//hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
	public List<Student> getAllStudents() {
		System.out.println("getAllStudents");
		return STUDENTS;
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println("registerNewUser");
		System.out.println(student);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable Long id) {
		System.out.println("deleteStudent");
		System.out.println(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
		System.out.println("updateStudent");
		System.out.println(String.format("%s %s", id, student));
	}

}
