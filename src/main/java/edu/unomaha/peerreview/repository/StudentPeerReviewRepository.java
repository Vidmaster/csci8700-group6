package edu.unomaha.peerreview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.StudentPeerReview;

public interface StudentPeerReviewRepository extends CrudRepository<StudentPeerReview, Integer>{
	
	List<StudentPeerReview> findByPeerReviewId(int id);
	
	List<StudentPeerReview> findByReviewerId(int id);
	
	List<StudentPeerReview> findByReviewerIdAndPeerReviewId(int rid, int pid);
	
}
