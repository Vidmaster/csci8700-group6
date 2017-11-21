package edu.unomaha.peerreview.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.unomaha.peerreview.model.Clazz;
import edu.unomaha.peerreview.model.PeerReview;
import edu.unomaha.peerreview.model.PeerReviewData;
import edu.unomaha.peerreview.model.ServiceResponse;
import edu.unomaha.peerreview.model.StudentGroup;
import edu.unomaha.peerreview.model.StudentPeerReview;
import edu.unomaha.peerreview.model.User;
import edu.unomaha.peerreview.repository.PeerReviewDataRepository;
import edu.unomaha.peerreview.repository.PeerReviewMetricRepository;
import edu.unomaha.peerreview.repository.PeerReviewRepository;
import edu.unomaha.peerreview.repository.StudentPeerReviewRepository;
import edu.unomaha.peerreview.repository.UserRepository;


@RestController
//@RequestMapping(path="/api/peerreview")
public class PeerReviewController {
	
	@Autowired
	private PeerReviewRepository peerreviewRepository;
	
	@Autowired
	private StudentPeerReviewRepository sprRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PeerReviewMetricRepository peerreviewMetricRepository;
	
	@Autowired
	private PeerReviewDataRepository peerreviewDataRepository;
	
////	@GetMapping(path="")
////	public String index() {
////	    //return "peerreview/index";
////		//return "test";
////		return "login";
////	}
//	
//	@GetMapping(path="/peerreview/professorview")
//	public String test3() {
//	    return "test";
//	}
//	
//	@GetMapping(path="/peerreview/studentview")
//	public String test2() {
//	    return "test2";
//	}
	
	@RequestMapping(value="/api/peerreview/all")
	public @ResponseBody Iterable<PeerReview> getAllPeerReviews() {
		return peerreviewRepository.findAll();
	}
	
	@GetMapping(value="/api/peerreview/read_one")
	public @ResponseBody PeerReview readOnePeerReview(@RequestParam int id) {
		return peerreviewRepository.findOne(id);
	}
	
	@RequestMapping(value="/api/peerreview/search")
	public @ResponseBody Iterable<PeerReview> searchPeerReviews(@RequestParam String s) {
		return null; // peerreviewRepository.findByPeerreviewNameContainingOrPeerreviewDescriptionContaining(s, s);
	}
	
	@PostMapping(value="/api/peerreview/delete")
	public @ResponseBody String deletePeerReview(@RequestBody PeerReview n) {
		peerreviewRepository.delete(n.getId());
		return "{\"message\" : \"peer review was deleted.\"}";
	}
	
	
	@RequestMapping(value = "/api/peerreview/add")
	public @ResponseBody String addNewPeerReview (@RequestParam String name
			, @RequestParam String description) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		PeerReview n = new PeerReview();
//		n.setPeerreviewName(name);
//		n.setPeerreviewDescription(description);
		peerreviewRepository.save(n);
		return "Peer Review Saved";
		
	}
	
	@RequestMapping(value = "/api/peerreview/create" , method = RequestMethod.POST)
	public @ResponseBody String createNewPeerReview (@RequestBody PeerReview n) {
		peerreviewRepository.save(n);
		return "{\"message\" : \"peer review was created.\"}";
	}
	
	@RequestMapping(value = "/api/peerreview/update" , method = RequestMethod.POST)
	public @ResponseBody String updatePeerReview (@RequestBody PeerReview n) {
		peerreviewRepository.save(n);
		return "{\"message\" : \"peer review was updated.\"}";
	}
	
	@PostMapping(value="/api/peerreview/run")
	public @ResponseBody String runPeerReview(@RequestBody PeerReview n) {
		
		int pid = n.getId();
		
		sendPeerReviewsToStudents(pid);
		
		return "{\"message\" : \"peer review was launched.\"}";
	}
	
	@RequestMapping(value="/api/peerreview/result")
	public @ResponseBody Iterable<PeerReviewData> getAllPeerReviewResults() {
		Iterable<PeerReviewData> datas = peerreviewDataRepository.findAll();
		
		return datas;
	}
	
	@RequestMapping(value="/api/peerreview/{id}/sendReviews")
	public ResponseEntity<ServiceResponse> sendPeerReviewsToStudents(@PathVariable("id") int id) {
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