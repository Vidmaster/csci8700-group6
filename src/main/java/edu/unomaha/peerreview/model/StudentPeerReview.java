package edu.unomaha.peerreview.model;

public class StudentPeerReview {
	int id;
	PeerReview pr;
	
	User reviewer;
	
	User student;
	
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
	
	
	
}
