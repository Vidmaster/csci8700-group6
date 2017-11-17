package edu.unomaha.peerreview.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.unomaha.peerreview.config.SecurityConfiguration;
import edu.unomaha.peerreview.model.Authority;
import edu.unomaha.peerreview.model.ServiceResponse;
import edu.unomaha.peerreview.model.User;
import edu.unomaha.peerreview.model.User.UserRole;
import edu.unomaha.peerreview.repository.AuthorityRepo;
import edu.unomaha.peerreview.repository.UserRepository;
import edu.unomaha.peerreview.utilities.AuthUtilities;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthorityRepo authorityRepo;
	
	@Autowired
	private AuthUtilities auth;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Secured(SecurityConfiguration.ProfessorRole)
	@RequestMapping(value="/api/students", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllStudents() {
		List<User> students = userRepo.findByUserRole(UserRole.STUDENT);
		
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> searchUsersByUsername(@RequestParam(value="username") String username) {
		List<User> users = new ArrayList<User>();
		userRepo.findByUsername(username).forEach(x -> { x.setPassword("PROTECTED"); users.add(x); });
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/users/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userRepo.findOne(id);
		user.setPassword("PROTECTED");

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/users", method=RequestMethod.POST)
	public ResponseEntity<ServiceResponse> createUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password, 
						@RequestParam(value="email") String email, @RequestParam(value="role") String role) {
		logger.debug("createuser(): username=" + username);
		UserRole userRole;
		Authority authority = new Authority(username, SecurityConfiguration.StudentRole);
		
		if (role.equals("S")) { 
			userRole = UserRole.STUDENT;
		} else if (role.equals("P")) {
			userRole = UserRole.PROFESSOR;
			authority.setAuthority(SecurityConfiguration.ProfessorRole);
		} else {
			logger.warn("Received invalid role: " + role);
			return new ResponseEntity<ServiceResponse>(new ServiceResponse("Unable to create user", false), HttpStatus.BAD_REQUEST);
		}
		
		try {
			String hashedPass = auth.encode(password);
			User user = new User(username, email, hashedPass, userRole, true);
			user = userRepo.save(user);
			authorityRepo.save(authority);
			
			return new ResponseEntity<ServiceResponse>(new ServiceResponse("Created user ID " + user.getId(), true), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error("Error creating user: " + ex.getMessage());
			return new ResponseEntity<ServiceResponse>(new ServiceResponse("Unable to create user", false), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/api/users/{id}", method=RequestMethod.PUT)
	public void updateUser(@PathVariable(value="id") int id, @RequestParam(value="email", defaultValue="") String email) {
		
		if (auth.isAuthorized(id)) {
			User user = userRepo.findOne(id);
			user.setEmail(email);
			
			userRepo.save(user);
		}
	}
	
	@RequestMapping(value="/api/users/{id}/updatePass", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateUserPassword(@PathVariable(value="id") int id, @RequestParam(value="password", defaultValue="") String password, 
			@RequestParam(value="newPassword", defaultValue="") String newPassword) throws Exception {
		
		User user = userRepo.findOne(id);
		String oldPass = user.getPassword();
		logger.debug("Updating password: " + password + ", " + newPassword + ", " + oldPass);
		logger.debug("Authorized: " + auth.isAuthorized(id));
		logger.debug("Matches: " + auth.matches(password, oldPass));
		if (auth.isAuthorized(id) && auth.matches(password, oldPass)) {
			String newPass = auth.encode(newPassword);
			user.setPassword(newPass);
			userRepo.save(user);
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@RequestMapping(value="/api/user/{id}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable(value="id") int id) {
		if (auth.isAuthorized(id)) {
			userRepo.delete(id);
		}
	}
}
