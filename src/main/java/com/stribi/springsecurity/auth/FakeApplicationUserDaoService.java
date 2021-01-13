package com.stribi.springsecurity.auth;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import static com.stribi.springsecurity.domain.ERole.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
	
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		
		return getApplicationUsers()
				.stream()
				.filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}
	
	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(
						"stribi", 
						passwordEncoder.encode("password"),
						STUDENT.getGrantedAuthorities(),
						true,
						true,
						true,
						true
						),
				new ApplicationUser(
						"gogi", 
						passwordEncoder.encode("password"),
						ADMIN.getGrantedAuthorities(),
						true,
						true,
						true,
						true
						),
				new ApplicationUser(
						"veki", 
						passwordEncoder.encode("password"),
						ADMINTRAINEE.getGrantedAuthorities(),
						true,
						true,
						true,
						true
						)
				);
		return  applicationUsers;
	}

}
