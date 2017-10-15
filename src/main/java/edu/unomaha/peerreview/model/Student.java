package edu.unomaha.peerreview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="student")
public class Student {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 @Column(name="student_id")
 private int id;
 
 @Column(name="student_name")
 private String studentName;
 
 @Column(name="student_emailaddress")
 private String studentEmailAddress;
 
 @Override
 public String toString() {
  return "Student [id=" + getId() + ", studentName=" + getStudentName()
    + ", studentEmailAddress=" + getStudentEmailAddress() + "]";
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
 * @return the studentName
 */
public String getStudentName() {
	return studentName;
}

/**
 * @param studentName the studentName to set
 */
public void setStudentName(String studentName) {
	this.studentName = studentName;
}

/**
 * @return the studentEmailAddress
 */
public String getStudentEmailAddress() {
	return studentEmailAddress;
}

/**
 * @param studentEmailAddress the studentEmailAddress to set
 */
public void setStudentEmailAddress(String studentEmailAddress) {
	this.studentEmailAddress = studentEmailAddress;
}
}