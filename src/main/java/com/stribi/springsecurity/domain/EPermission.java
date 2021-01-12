package com.stribi.springsecurity.domain;

public enum EPermission {
	
	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");
	
	private final String permission;

	EPermission(String permission) {
		this.permission = permission;
		
	}

	public String getPermission() {
		return permission;
	}
	
	
	
	

}
