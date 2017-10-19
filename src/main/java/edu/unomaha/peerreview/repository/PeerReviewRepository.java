package edu.unomaha.peerreview.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import edu.unomaha.peerreview.model.PeerReview;

// This will be AUTO IMPLEMENTED by Spring into a Bean called PeerReviewRepository
// CRUD refers Create, Read, Update, Delete

public interface PeerReviewRepository extends CrudRepository<PeerReview, Integer> {
	
	List<PeerReview> findByPeerreviewNameContainingOrPeerreviewDescriptionContaining (String n, String d);

}