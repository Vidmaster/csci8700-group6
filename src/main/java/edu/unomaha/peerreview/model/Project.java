package edu.unomaha.peerreview.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	int id;
	int classId;
	String projectName;
	List<StudentGroup> groups;
}
