package edu.unomaha.peerreview.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table(name="peerreview_metric")
public class PeerReviewMetric {
	public enum PeerReviewMetricType {
		TEXT, ORDINAL, NUMERIC
	}
	
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="peerreview_metric_id")
 private int id;
 
 @Column(name="peerreview_metric_definition")
 private String peerreviewMetricDefinition;
 
 @Enumerated(EnumType.STRING)
 private PeerReviewMetricType peerreviewMetricType;
 
 @OneToMany(mappedBy = "id")
 private List<PeerReviewData> peerreviewData;
 
 @Override
 public String toString() {
  return "PeerReviewMetric [id=" + getId() + ", peerreviewMetricDefinition=" + getPeerreviewMetricDefinition()
    + ", peerreviewMetricType=" + getPeerreviewMetricType() + "]";
 }

/**
 * @return the id
 */
public int getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}

/**
 * @return the peerreviewMetricDefinition
 */
public String getPeerreviewMetricDefinition() {
	return peerreviewMetricDefinition;
}

/**
 * @param peerreviewMetricDefinition the peerreviewMetricDefinition to set
 */
public void setPeerreviewMetricDefinition(String peerreviewMetricDefinition) {
	this.peerreviewMetricDefinition = peerreviewMetricDefinition;
}

/**
 * @return the peerreviewMetricType
 */
public PeerReviewMetricType getPeerreviewMetricType() {
	return peerreviewMetricType;
}

/**
 * @param peerreviewMetricType the peerreviewMetricType to set
 */
public void setPeerreviewMetricType(PeerReviewMetricType peerreviewMetricType) {
	this.peerreviewMetricType = peerreviewMetricType;
}
}