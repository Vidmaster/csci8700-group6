package edu.unomaha.peerreview.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.unomaha.peerreview.config.SecurityConfiguration;
import edu.unomaha.peerreview.database.UserDao;
import edu.unomaha.peerreview.domain.ServiceResponse;
import edu.unomaha.peerreview.domain.User;
import edu.unomaha.peerreview.utilities.AuthUtilities;

@RestController
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AuthUtilities auth;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/api/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> searchUsersByUsername(@RequestParam(value="username") String username) {
		List<User> users = userDao.searchByUsername(username); 
		
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/users/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = userDao.readUser(id);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/users", method=RequestMethod.POST)
	public ServiceResponse createUser(@RequestParam(value="username") String username, @RequestParam(value="password") String password, 
						@RequestParam(value="email") String email, @RequestParam(value="role") String role) {
		logger.debug("createuser(): username=" + username);
		if (role.equals("S")) { 
			role = SecurityConfiguration.StudentRole;
		} else if (role.equals("P")) {
			role = SecurityConfiguration.ProfessorRole;
		} else {
			logger.warn("Received invalid role: " + role);
			return new ServiceResponse("Unable to create user", false);
		}
		
		try {
			String hashedPass = auth.encode(password);
			User user = new User(-1, username, email, hashedPass, true);
			Number id = userDao.createUser(user, role);
			
			return new ServiceResponse("Created user ID " + id, true);
		} catch (Exception ex) {
			logger.error("Error creating user: " + ex.getMessage());
			return new ServiceResponse("Unable to create user", false);
		}
	}
	
	@RequestMapping(value="/api/users/{id}", method=RequestMethod.PUT)
	public void updateUser(@PathVariable(value="id") int id, @RequestParam(value="email", defaultValue="") String email) {
		
		if (auth.isAuthorized(id)) {
			User user = userDao.readUser(id);
			user.setEmail(email);
			
			userDao.updateUser(id, user);
		}
	}
	
	@RequestMapping(value="/api/users/{id}/updatePass", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateUserPassword(@PathVariable(value="id") int id, @RequestParam(value="password", defaultValue="") String password, 
			@RequestParam(value="newPassword", defaultValue="") String newPassword) throws Exception {
		User user = userDao.readUserWithPass(id);
		String oldPass = user.getPassword();
		logger.debug("Updating password: " + password + ", " + newPassword + ", " + oldPass);
		logger.debug("Authorized: " + auth.isAuthorized(id));
		logger.debug("Matches: " + auth.matches(password, oldPass));
		if (auth.isAuthorized(id) && auth.matches(password, oldPass)) {
			String newPass = auth.encode(newPassword);
			userDao.updateUserPassword(id, newPass);
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@RequestMapping(value="/api/user/{id}", method=RequestMethod.DELETE)
	public void deleteUser(@PathVariable(value="id") int id) {
		if (auth.isAuthorized(id)) {
			userDao.deleteUser(id);
		}
	}
}
