package edu.unomaha.peerreview.repository;

import org.springframework.data.repository.CrudRepository;
import edu.unomaha.peerreview.model.PeerReviewData;
import edu.unomaha.peerreview.model.StudentPeerReview;

// This will be AUTO IMPLEMENTED by Spring into a Bean called PeerReviewRepository
// CRUD refers Create, Read, Update, Delete

public interface PeerReviewDataRepository extends CrudRepository<PeerReviewData, Integer> {
	
	long deleteByStudentPeerReview(StudentPeerReview spr);
	
	
}