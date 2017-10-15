package edu.unomaha.peerreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.unomaha.peerreview.model.PeerReviewMetric;
import edu.unomaha.peerreview.repository.PeerReviewMetricRepository;


@Controller
public class PeerReviewMetricController {
	
	@Autowired
	private PeerReviewMetricRepository peerreviewMetricRepository;
	
	@RequestMapping(value="/api/metric/all")
	public @ResponseBody Iterable<PeerReviewMetric> getAllMetrics() {
		return peerreviewMetricRepository.findAll();
	}
	
	@GetMapping(value="/api/metric/read_one")
	public @ResponseBody PeerReviewMetric readOneMetric(@RequestParam int id) {
		return peerreviewMetricRepository.findOne(id);
	}
	
	@RequestMapping(value="/api/metric/search")
	public @ResponseBody Iterable<PeerReviewMetric> searchMetrics(@RequestParam String s) {
		return peerreviewMetricRepository.findByPeerreviewMetricDefinitionContainingOrPeerreviewMetricTypeContaining(s, s);
	}
	
	@PostMapping(value="/api/metric/delete")
	public @ResponseBody String deleteMetric(@RequestBody PeerReviewMetric n) {
		peerreviewMetricRepository.delete(n.getId());
		return "{\"message\" : \"metric was deleted.\"}";
	}
	
	
	@RequestMapping(value = "/api/metric/create" , method = RequestMethod.POST)
	public @ResponseBody String createNewMetric (@RequestBody PeerReviewMetric n) {
		peerreviewMetricRepository.save(n);
		return "{\"message\" : \"metric was created.\"}";
	}
	
	@RequestMapping(value = "/api/metric/update" , method = RequestMethod.POST)
	public @ResponseBody String updateMetric (@RequestBody PeerReviewMetric n) {
		peerreviewMetricRepository.save(n);
		return "{\"message\" : \"metric was updated.\"}";
	}
}