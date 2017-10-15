package edu.unomaha.peerreview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="peerreview_metric")
public class PeerReviewMetric {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="peerreview_metric_id")
 private int id;
 
 @Column(name="peerreview_metric_definition")
 private String peerreviewMetricDefinition;
 
 @Column(name="peerreview_metric_type")
 private String peerreviewMetricType;
 
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
public String getPeerreviewMetricType() {
	return peerreviewMetricType;
}

/**
 * @param peerreviewMetricType the peerreviewMetricType to set
 */
public void setPeerreviewMetricType(String peerreviewMetricType) {
	this.peerreviewMetricType = peerreviewMetricType;
}
}