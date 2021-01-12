package com.stribi.springsecurity.domain;

public class Student {
	
	private Long id;
	private String name;
	
	
	public Student(Long id, String name) {
		this.id = id;
		this.name = name;
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	

}
