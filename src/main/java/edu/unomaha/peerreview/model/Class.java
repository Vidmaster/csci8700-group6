package edu.unomaha.peerreview.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="class")
public class Class {
	int id;
	String name;
	int instructorId;
	List<User> students;
	List<Project> projects;
}
