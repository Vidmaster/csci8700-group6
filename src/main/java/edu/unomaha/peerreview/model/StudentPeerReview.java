package edu.unomaha.peerreview.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="student_peer_review")
public class StudentPeerReview {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@ManyToOne(targetEntity = PeerReview.class)
	private PeerReview peerReview;
	
	@ManyToOne(targetEntity = User.class)
	private User reviewer;
	
	@ManyToOne(targetEntity = User.class)
	private User student;
	
	@Column
	private boolean submitted;
	
	@OneToMany(mappedBy="id")
	private List<PeerReviewData> responses;
	
	public List<PeerReviewData> getResponses() {
		return responses;
	}

	public void setResponses(List<PeerReviewData> responses) {
		this.responses = responses;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public PeerReview getPeerReview() {
		return peerReview;
	}
	
	public void setPeerReview(PeerReview pr) {
		this.peerReview = pr;
	}
	
	public int getPeerReviewId() {
		return peerReview.getId();
	}
	
	public User getReviewer() {
		return reviewer;
	}
	
	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}
	
	public int getReviewerId() {
		return reviewer.getId();
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
