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
	@JoinColumn(name="peer_review_id")
	@JsonView
	private StudentPeerReview studentPeerReview;
	 
	@ManyToOne
	@JoinColumn(name="peerreview_metric_id")
	@JsonView
	private PeerReviewMetric peerreview_metric;
	 
	@Column(name="peerreview_result")
	private String peerreviewResult;
	
	@Override
	public String toString() {
		return "PeerrReviewData [id=" + getId()
			+ ", pid=" + studentPeerReview.getId()
			+ ", pname=" + studentPeerReview.getPeerReview().getDescription()
			+ ", rid=" + studentPeerReview.getReviewer().getId()
			+ ", sid=" + studentPeerReview.getStudent().getId()
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

	public StudentPeerReview getStudentPeerReview() {
		return studentPeerReview;
	}

	public void setStudentPeerReview(StudentPeerReview studentPeerReview) {
		this.studentPeerReview = studentPeerReview;
	}

	public int getStudentPeerReviewId() {
		return studentPeerReview.getId();
	}
	
	public PeerReviewMetric getPeerreview_metric() {
		return peerreview_metric;
	}

	public void setPeerreview_metric(PeerReviewMetric peerreview_metric) {
		this.peerreview_metric = peerreview_metric;
	}

}