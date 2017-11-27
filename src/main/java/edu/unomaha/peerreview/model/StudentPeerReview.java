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
	int id;
	
	@ManyToOne(targetEntity = PeerReview.class)
	PeerReview pr;
	
	@ManyToOne(targetEntity = User.class)
	User reviewer;
	
	@ManyToOne(targetEntity = User.class)
	User student;
	
	@Column
	boolean submitted;
	
	@OneToMany(mappedBy="id")
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
