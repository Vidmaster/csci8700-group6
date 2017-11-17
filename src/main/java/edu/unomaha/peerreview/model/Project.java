package edu.unomaha.peerreview.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	int id;

	@ManyToOne
	@JoinColumn(name = "classId")
	Clazz clazz;
	
	@Column
	String projectName;

	@Column
	String description;
	
	@OneToMany(mappedBy = "id")
	List<StudentGroup> groups;
	
	@OneToMany(mappedBy = "id")
	List<PeerReview> peerReviews;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Clazz getClassId() {
		return clazz;
	}

	public void setClassId(Clazz clazz) {
		this.clazz = clazz;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<StudentGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<StudentGroup> groups) {
		this.groups = groups;
	}
}
