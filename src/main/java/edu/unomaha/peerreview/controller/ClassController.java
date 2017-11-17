package edu.unomaha.peerreview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.unomaha.peerreview.config.SecurityConfiguration;
import edu.unomaha.peerreview.model.Clazz;
import edu.unomaha.peerreview.model.ServiceResponse;
import edu.unomaha.peerreview.model.User;
import edu.unomaha.peerreview.repository.ClassRepository;
import edu.unomaha.peerreview.utilities.AuthUtilities;

@RestController
public class ClassController {
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	AuthUtilities auth;
	
	@Secured(SecurityConfiguration.ProfessorRole)
	@RequestMapping(value="/class", method=RequestMethod.POST)
	public ResponseEntity<ServiceResponse> createClass(@RequestBody Clazz clazz) {
		classRepository.save(clazz);
	
		return new ResponseEntity<>(new ServiceResponse("OK", true), HttpStatus.OK);
	}

	@Secured(SecurityConfiguration.ProfessorRole)
	@RequestMapping(value="/class/{id}/students", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getClassStudents(@PathVariable int id) {
		List<User> users = classRepository.findOne(id).getStudents();
		users.forEach(u -> u.setPassword("PROTECTED"));
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@Secured(SecurityConfiguration.ProfessorRole)
	@RequestMapping(value="/class/{id}", method=RequestMethod.GET)
	public ResponseEntity<Clazz> getClazz(@PathVariable int id) {
		Clazz c = classRepository.findOne(id);
		
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	@Secured(SecurityConfiguration.ProfessorRole)
	@RequestMapping(value="/class/{id}", method=RequestMethod.PUT)
	public ResponseEntity<ServiceResponse> updateClass(@PathVariable int id, @RequestBody Clazz c) {
		Clazz oldClass = classRepository.findOne(id);
		
		if (auth.isAuthorized(oldClass.getInstructor().getId())) {
			classRepository.save(c);
			
			return new ResponseEntity<>(new ServiceResponse("OK", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ServiceResponse("UNAUTHORIZED", false), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@Secured(SecurityConfiguration.ProfessorRole)
	@RequestMapping(value="/class/{id}/addStudent", method=RequestMethod.POST)
	public ResponseEntity<ServiceResponse> addStudent(@PathVariable int id, @RequestBody User student) {
		Clazz c = classRepository.findOne(id);
		c.getStudents().add(student);
		classRepository.save(c);
		
		return new ResponseEntity<>(new ServiceResponse("OK", true), HttpStatus.OK);
	}
}
