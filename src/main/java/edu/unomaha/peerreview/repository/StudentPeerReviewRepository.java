package edu.unomaha.peerreview.repository;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.StudentPeerReview;

public interface StudentPeerReviewRepository extends CrudRepository<StudentPeerReview, Integer>{

}
