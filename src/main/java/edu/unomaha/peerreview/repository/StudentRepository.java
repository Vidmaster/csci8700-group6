package edu.unomaha.peerreview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.Student;

// This will be AUTO IMPLEMENTED by Spring into a Bean called StudentwRepository
// CRUD refers Create, Read, Update, Delete

public interface StudentRepository extends CrudRepository<Student, Integer> {

	List<Student> findByStudentNameContainingOrStudentEmailAddressContaining (String n, String d);
}
