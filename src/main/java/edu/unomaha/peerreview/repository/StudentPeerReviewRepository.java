package edu.unomaha.peerreview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.PeerReview;
import edu.unomaha.peerreview.model.StudentPeerReview;
import edu.unomaha.peerreview.model.User;

public interface StudentPeerReviewRepository extends CrudRepository<StudentPeerReview, Integer>{
	
	List<StudentPeerReview> findByPeerReview(PeerReview pr);
	
	List<StudentPeerReview> findByReviewer(User r);
	
	List<StudentPeerReview> findByReviewerAndPeerReview(User r, PeerReview pr);
	
}
