package edu.unomaha.peerreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.unomaha.peerreview.model.PeerReviewMetric;
import edu.unomaha.peerreview.repository.PeerReviewMetricRepository;


@Controller
public class PeerReviewQuestionnaireController {
	
	@Autowired
	private PeerReviewMetricRepository peerreviewQuestionnaireRepository;
	
	@RequestMapping(value="/api/questionnaire/all")
	public @ResponseBody Iterable<PeerReviewMetric> getAllMetrics() {
		return peerreviewQuestionnaireRepository.findAll();
	}
}