package edu.unomaha.peerreview.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import edu.unomaha.peerreview.repository.PeerReviewRepository;

@Entity
@Table(name="student_group")
public class StudentGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	int id;
	
	@ManyToOne
	@JoinColumn(name="projectId")
	Project project;
	
	@Column
	String groupName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "member_to_group", 
	        joinColumns = { @JoinColumn(name = "id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "projectId") }
	    )
	List<User> members;
	
	public Map<User, List<StudentPeerReview>> createReviews(PeerReview pr) {
		Map<User, List<StudentPeerReview>> m = new HashMap<>();
		
		for (User student : members) {
			List<StudentPeerReview> reviews = new ArrayList<>();
			
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
			
			m.put(student, reviews);
		}

		return m;
	}
	
}
