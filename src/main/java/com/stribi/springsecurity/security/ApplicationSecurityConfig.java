package com.stribi.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import static com.stribi.springsecurity.domain.ERole.ADMIN;
import static com.stribi.springsecurity.domain.ERole.STUDENT;
import static com.stribi.springsecurity.domain.ERole.ADMINTRAINEE;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				.antMatchers("/api/**").hasAnyRole(STUDENT.name())
				//.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
				//.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
				//.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
				//.antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails gogi = User.builder()
				.username("gogi")
				.password(passwordEncoder.encode("password"))
				// .roles(STUDENT.name())
				.authorities(STUDENT.getGrantedAuthorities())
				.build();

		UserDetails stribi = User.builder()
				.username("stribi")
				.password(passwordEncoder.encode("password"))
				// .roles(ADMIN.name())
				.authorities(ADMIN.getGrantedAuthorities())
				.build();

		UserDetails john = User.builder().username("john")
				.password(passwordEncoder.encode("password"))
				// .roles(ADMINTRAINEE.name())
				.authorities(ADMINTRAINEE.getGrantedAuthorities())
				.build();

		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return stribi;
			}
		};

	}

}
