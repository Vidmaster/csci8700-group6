package edu.unomaha.peerreview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="peerreview")
public class PeerReview {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="peerreview_id")
 private int id;
 
 @Column(name="peerreview_name")
 private String peerreviewName;
 
 @Column(name="peerreview_description")
 private String peerreviewDescription;
 
 @Override
 public String toString() {
  return "PeerrReview [id=" + getId() + ", peerreviewName=" + getPeerreviewName()
    + ", peerreviewDescription=" + getPeerreviewDescription() + "]";
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
 * @return the peerreviewName
 */
public String getPeerreviewName() {
	return peerreviewName;
}

/**
 * @param peerreviewName the peerreviewName to set
 */
public void setPeerreviewName(String peerreviewName) {
	this.peerreviewName = peerreviewName;
}

/**
 * @return the peerreviewDescription
 */
public String getPeerreviewDescription() {
	return peerreviewDescription;
}

/**
 * @param peerreviewDescription the peerreviewDescription to set
 */
public void setPeerreviewDescription(String peerreviewDescription) {
	this.peerreviewDescription = peerreviewDescription;
}
}