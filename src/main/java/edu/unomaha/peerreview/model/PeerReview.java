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
@Table(name="peerreview")
public class PeerReview {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="id")
	private int id;
	
	@ManyToOne(targetEntity = Clazz.class)
	private Clazz clazz;
	
	@OneToMany(mappedBy = "id")
	private List<PeerReviewMetric> metrics;

	@OneToMany(mappedBy="id")
	private List<StudentGroup> groups;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<PeerReviewMetric> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<PeerReviewMetric> metrics) {
		this.metrics = metrics;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StudentGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<StudentGroup> groups) {
		this.groups = groups;
	}
	
	
}
