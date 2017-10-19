package edu.unomaha.peerreview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.PeerReviewMetric;

// This will be AUTO IMPLEMENTED by Spring into a Bean called PeerReviewMetricRepository
// CRUD refers Create, Read, Update, Delete

public interface PeerReviewMetricRepository extends CrudRepository<PeerReviewMetric, Integer> {
	
	List<PeerReviewMetric> findByPeerreviewMetricDefinitionContainingOrPeerreviewMetricTypeContaining (String n, String p);
}