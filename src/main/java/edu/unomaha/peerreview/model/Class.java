package edu.unomaha.peerreview.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="class")
public class Class {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	int id;
	
	@Column
	String name;
	
	@ManyToOne
	@JoinColumn(name="instructorId")
	User instructor;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name = "student_to_class", 
	        joinColumns = { @JoinColumn(name = "studentId") }, 
	        inverseJoinColumns = { @JoinColumn(name = "classId") }
	    )
	List<User> students;
	
	@OneToMany(mappedBy = "id")
	List<Project> projects;
}
