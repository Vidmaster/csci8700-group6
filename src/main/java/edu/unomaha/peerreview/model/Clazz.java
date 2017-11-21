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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="class")
public class Clazz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	int id;
	
	@Column
	String name;
	
	@ManyToOne
	@JoinColumn(name="instructorId")
	User instructor;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "student_to_class", 
	        joinColumns = { @JoinColumn(name = "studentId") }, 
	        inverseJoinColumns = { @JoinColumn(name = "classId") }
	    )
	List<User> students = new ArrayList<>();
	
	@OneToMany(mappedBy = "id")
	List<PeerReview> peerReviews;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getInstructor() {
		return instructor;
	}

	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}

	public List<User> getStudents() {
		return students;
	}

	public void setStudents(List<User> students) {
		this.students = students;
	}

	public List<PeerReview> getPeerReviews() {
		return peerReviews;
	}

	public void setPeerReviews(List<PeerReview> peerReviews) {
		this.peerReviews = peerReviews;
	}
	
	
}
