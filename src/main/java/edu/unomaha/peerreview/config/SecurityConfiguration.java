package edu.unomaha.peerreview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import edu.unomaha.peerreview.utilities.AuthUtilities;
import edu.unomaha.peerreview.utilities.JdbcUserDetailsService;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	private static String[] PERMITTED_URLS = {"/", "/index.html", "/test.html", "/test2.html", "/webjars/**", 
			"/js/**", "/font-awesome/**", "/user",
			"/css/**", "/views/**", "/img/**", "/#!/**", "/login", "/api/**", "/favicon.ico", "/app/**"};
	
	public static final String StudentRole = "ROLE_STUDENT";
	public static final String ProfessorRole = "ROLE_PROFESSOR";
	public static final String AdminRole = "ROLE_ADMIN";
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic()
			.and()
				.authorizeRequests().antMatchers(PERMITTED_URLS).permitAll().anyRequest().authenticated()
			.and()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthUtilities auth() {
		return new AuthUtilities();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new JdbcUserDetailsService();
	}
	
}
