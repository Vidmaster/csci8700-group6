package edu.unomaha.peerreview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="peerreview_data")
public class PeerReviewData {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="peerreview_data_id")
 private int id;

 @ManyToOne
 @JoinColumn(name="peerreview_id")
 @JsonView
 private PeerReview peerreview;
 
 @ManyToOne
 @JoinColumn(name="reviewer_id", referencedColumnName="id")
 @JsonView
 private User reviewer;
 
 @ManyToOne
 @JoinColumn(name="id")
 @JsonView
 private User student;
 
 @ManyToOne
 @JoinColumn(name="peerreview_metric_id")
 @JsonView
 private PeerReviewMetric peerreview_metric;
 
 @Column(name="peerreview_result")
 private String peerreviewResult;
 
 @Override
 public String toString() {
  return "PeerrReviewData [id=" + getId()
    + ", pid=" + peerreview.getId()
    + ", pname=" + peerreview.getPeerreviewName()
    + ", rid=" + getRid()
    + ", sid=" + getSid()
    + ", peerreviewResult=" + getPeerreviewResult()
    + "]";
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

public int getPid() {
	return peerreview.getId();
}

public void setPid(PeerReview pid) {
	this.peerreview = pid;
}

public String getPname() {
	return peerreview.getPeerreviewName();
}

public void setPname(String pname) {
	this.peerreview.setPeerreviewName(pname);
}

public int getRid() {
	return reviewer.getId();
}

public void setRid(User rid) {
	this.reviewer = rid;
}

public String getRname() {
	return reviewer.getUsername();
}

public void setRname(String rname) {
	this.reviewer.setUsername(rname);
}

public int getSid() {
	return student.getId();
}

public void setSid(User sid) {
	this.student = sid;
}

public String getSname() {
	return student.getUsername();
}

public void setSname(String sname) {
	this.student.setUsername(sname);
}

public int getMid() {
	return peerreview_metric.getId();
}

public void setMid(PeerReviewMetric mid) {
	this.peerreview_metric = mid;
}

public String getMname() {
	return peerreview_metric.getPeerreviewMetricDefinition();
}

public void setMname(String mname) {
	this.peerreview_metric.setPeerreviewMetricDefinition(mname);
}

public String getPeerreviewResult() {
	return peerreviewResult;
}

public void setPeerreviewResult(String peerreviewResult) {
	this.peerreviewResult = peerreviewResult;
}

}