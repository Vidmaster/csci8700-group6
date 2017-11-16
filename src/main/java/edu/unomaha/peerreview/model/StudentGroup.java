package edu.unomaha.peerreview.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="student_group")
public class StudentGroup {
	int id;
	int projectId;
	String groupName;
	List<User> members;
}
