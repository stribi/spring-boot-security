package com.stribi.springsecurity.domain;

import java.util.Set;
import com.google.common.collect.Sets;
import static com.stribi.springsecurity.domain.EPermission.*;

public enum ERole {
	
	ADMIN(Sets.newHashSet()),
	STUDENT(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));
	
	private final Set<EPermission> permissions;

	private ERole(Set<EPermission> permissions) {
		this.permissions = permissions;
	}
	
	public Set<EPermission> getPermissions() {
		return permissions;
	}

}
