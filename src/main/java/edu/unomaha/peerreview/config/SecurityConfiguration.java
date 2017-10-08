package edu.unomaha.peerreview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration {

	public static final String StudentRole = "ROLE_STUDENT";
	public static final String ProfessorRole = "ROLE_PROFESSOR";
	public static final String AdminRole = "ROLE_ADMIN";
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
