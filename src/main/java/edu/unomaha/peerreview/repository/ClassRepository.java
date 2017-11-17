package edu.unomaha.peerreview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.Clazz;

public interface ClassRepository extends CrudRepository<Clazz, Integer> {

	List<Clazz> findByInstructorId(int id);
	
}
