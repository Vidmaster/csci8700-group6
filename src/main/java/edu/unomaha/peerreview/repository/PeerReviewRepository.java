package edu.unomaha.peerreview.repository;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.PeerReview;

public interface PeerReviewRepository extends CrudRepository<PeerReview, Integer> {

	//Iterable<PeerReview> findByPeerreviewNameContainingOrPeerreviewDescriptionContaining(String s, String s2);

}
