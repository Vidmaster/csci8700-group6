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
	int id;
	
	@ManyToOne(targetEntity = Project.class)
	Project project;
	
	@OneToMany(mappedBy = "id")
	List<PeerReviewMetric> metrics;

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
	
}
