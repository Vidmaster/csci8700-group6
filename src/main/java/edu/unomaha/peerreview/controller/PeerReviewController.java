package edu.unomaha.peerreview.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unomaha.peerreview.config.SecurityConfiguration;
import edu.unomaha.peerreview.model.Clazz;
import edu.unomaha.peerreview.model.PeerReview;
import edu.unomaha.peerreview.model.ServiceResponse;
import edu.unomaha.peerreview.model.StudentGroup;
import edu.unomaha.peerreview.model.StudentPeerReview;
import edu.unomaha.peerreview.model.User;
import edu.unomaha.peerreview.repository.PeerReviewDataRepository;
import edu.unomaha.peerreview.repository.PeerReviewRepository;
import edu.unomaha.peerreview.repository.StudentPeerReviewRepository;
import edu.unomaha.peerreview.repository.UserRepository;
import edu.unomaha.peerreview.utilities.AuthUtilities;


@RestController
public class PeerReviewController {
	
	@Autowired
	private PeerReviewRepository peerreviewRepository;
	
	@Autowired
	private StudentPeerReviewRepository sprRepository;
	
	@Autowired
	private PeerReviewDataRepository prDataRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthUtilities auth;
	
	@GetMapping(value="/api/peerreview/{id}")
	public @ResponseBody PeerReview readOnePeerReview(@RequestParam int id) {
		return peerreviewRepository.findOne(id);
	}
	
	@RequestMapping(value="/api/peerreview")
	public @ResponseBody Iterable<PeerReview> searchPeerReviews(@RequestParam(defaultValue="") String s) {
		if (s.isEmpty()) {
			return peerreviewRepository.findAll();
		} else {
			return peerreviewRepository.findByNameContainingOrDescriptionContaining(s, s);
		}
	}
	
	@DeleteMapping(value="/api/peerreview/{id}")
	@Secured(SecurityConfiguration.ProfessorRole)
	public ResponseEntity<ServiceResponse> deletePeerReview(@PathVariable int id) {
		PeerReview pr = peerreviewRepository.findOne(id);
		if (pr == null) {
			return new ResponseEntity<>(new ServiceResponse("Peer review " + id + " does not exist", false), HttpStatus.NOT_FOUND);
		} else if (auth.isAuthorized(pr.getClazz().getInstructor().getId())) {
			peerreviewRepository.delete(id);
			return new ResponseEntity<>(new ServiceResponse("Peer review " + id + " was deleted.", true), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ServiceResponse("Not Authorized", false), HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	@PostMapping(value = "/api/peerreview")
	@Secured(SecurityConfiguration.ProfessorRole)
	public ResponseEntity<ServiceResponse> addNewPeerReview (@RequestBody PeerReview pr) {
		peerreviewRepository.save(pr);
		return new ResponseEntity<>(new ServiceResponse(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/api/peerreview/{id}" , method = RequestMethod.PUT)
	public ResponseEntity<ServiceResponse> updatePeerReview (@RequestBody PeerReview n) {
		PeerReview pr = peerreviewRepository.findOne(n.getId());
		if (pr == null) {
			return new ResponseEntity<>(new ServiceResponse("Peer review " + n.getId() + " does not exist", false), HttpStatus.NOT_FOUND);
		} else if (auth.isAuthorized(pr.getClazz().getInstructor().getId())) {
			peerreviewRepository.save(n);
			return new ResponseEntity<>(new ServiceResponse(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ServiceResponse("Not Authorized", false), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping(value="/api/peerreview/{id}/run")
	@Secured(SecurityConfiguration.ProfessorRole)
	public ResponseEntity<ServiceResponse> runPeerReview(@PathVariable int id) {
		PeerReview pr = peerreviewRepository.findOne(id);
		if (pr == null) {
			return new ResponseEntity<>(new ServiceResponse("Peer review " + id + " does not exist", false), HttpStatus.NOT_FOUND);
		} else if (auth.isAuthorized(pr.getClazz().getInstructor().getId())) {
			return sendPeerReviewsToStudents(id);
		} else {
			return new ResponseEntity<>(new ServiceResponse("Not Authorized", false), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value="/api/peerreview/{id}/results")
	@Secured(SecurityConfiguration.ProfessorRole)
	public ResponseEntity<List<StudentPeerReview>> getAllPeerReviewResults(@PathVariable int id) {
		PeerReview pr = peerreviewRepository.findOne(id);
		List<StudentPeerReview> data = sprRepository.findByPeerReview(pr);
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/peerreview/{id}/student")
	@Secured(SecurityConfiguration.StudentRole)
	public ResponseEntity<List<StudentPeerReview>> getStudentPeerReviews(@PathVariable int pid) {
		int userId = auth.getActiveUser();
		User u = userRepository.findOne(userId);
		PeerReview pr = peerreviewRepository.findOne(pid);
		List<StudentPeerReview> data = sprRepository.findByReviewerAndPeerReview(u, pr);
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/peerreview/student")
	@Secured(SecurityConfiguration.StudentRole)
	public ResponseEntity<List<StudentPeerReview>> getMyPeerReviews() {
		int userId = auth.getActiveUser();
		User u = userRepository.findOne(userId);
		List<StudentPeerReview> data = sprRepository.findByReviewer(u);
		
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/peerreview/student/{id}")
	@Secured(SecurityConfiguration.StudentRole)
	public ResponseEntity<ServiceResponse> submitStudentPeerReview(@PathVariable int id) {
		StudentPeerReview spr = sprRepository.findOne(id);
		
		if (spr == null) {
			return new ResponseEntity<>(new ServiceResponse("Student peer review " + id + " does not exist", false), HttpStatus.NOT_FOUND);
		} else if (auth.isAuthorized(spr.getReviewerId())) {
			spr.setSubmitted(true);
			prDataRepository.save(spr.getResponses());
			sprRepository.save(spr);
			
			return new ResponseEntity<>(new ServiceResponse(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ServiceResponse("Not Authorized", false), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@RequestMapping(value="/api/peerreview/{pid}/resetstudentreview")
	@Secured(SecurityConfiguration.ProfessorRole)
	public ResponseEntity<ServiceResponse> resetPeerReview(@PathVariable int pid, @RequestParam int id) {
		PeerReview pr = peerreviewRepository.findOne(pid);
		if (pr == null) {
			return new ResponseEntity<>(new ServiceResponse("Peer review " + pid + " does not exist", false), HttpStatus.NOT_FOUND);
		} else if (auth.isAuthorized(pr.getClazz().getInstructor().getId())) {
			StudentPeerReview spr = sprRepository.findOne(id);
			spr.setSubmitted(false);
			sprRepository.save(spr);
			// Delete any data created by the student for this review
			prDataRepository.deleteByStudentPeerReview(spr);
			
			return new ResponseEntity<>(new ServiceResponse(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ServiceResponse("Not Authorized", false), HttpStatus.UNAUTHORIZED);
		}
	}
	
	public ResponseEntity<ServiceResponse> sendPeerReviewsToStudents(int id) {
		PeerReview pr = peerreviewRepository.findOne(id);
		Clazz c = pr.getClazz();
		List<User> students = c.getStudents();

		Set<User> groupMembers = new HashSet<User>();
		
		for (StudentGroup g : pr.getGroups()) {
			for (User s : g.getMembers()) {
				groupMembers.add(s);
			}
		}
		
		for (User student : students) {
			if (!groupMembers.contains(student)) {
				return new ResponseEntity<>(new ServiceResponse("Student " + student.getUsername() + " is not in a group.", false), HttpStatus.OK);
			}
		}
		
		for (StudentGroup g : pr.getGroups()) {
			List<StudentPeerReview> reviews = g.createReviews(pr);
			sprRepository.save(reviews);
		}
		
		return new ResponseEntity<>(new ServiceResponse("Peer review notifications generated", true), HttpStatus.OK);
	}
}