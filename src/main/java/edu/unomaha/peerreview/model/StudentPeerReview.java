package edu.unomaha.peerreview.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class StudentPeerReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	int id;
	
	PeerReview pr;
	
	User reviewer;
	
	User student;
	
	@Column
	boolean submitted;
	
	
	List<PeerReviewData> responses;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public PeerReview getPr() {
		return pr;
	}
	
	public void setPr(PeerReview pr) {
		this.pr = pr;
	}
	
	public User getReviewer() {
		return reviewer;
	}
	
	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}
	
	public User getStudent() {
		return student;
	}
	
	public void setStudent(User student) {
		this.student = student;
	}

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
	
}
