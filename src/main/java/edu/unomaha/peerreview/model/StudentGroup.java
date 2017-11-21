package edu.unomaha.peerreview.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="student_group")
public class StudentGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	int id;
	
	@ManyToOne
	@JoinColumn(name="peerReviewId")
	PeerReview peerReview;
	
	@Column
	String groupName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "member_to_group", 
	        joinColumns = { @JoinColumn(name = "id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "projectId") }
	    )
	List<User> members;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PeerReview getPeerReview() {
		return peerReview;
	}

	public void setPeerReview(PeerReview peerReview) {
		this.peerReview = peerReview;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<StudentPeerReview> createReviews(PeerReview pr) {
		List<StudentPeerReview> reviews = new ArrayList<>();
		
		for (User student : members) {
			for (User groupMember : members) {
				if (student == groupMember) {
					continue;
				}
				StudentPeerReview p = new StudentPeerReview();
				p.setReviewer(student);
				p.setStudent(groupMember);
				p.setPr(pr);
				
				reviews.add(p);
			}
		}

		return reviews;
	}
	
}
