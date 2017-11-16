package edu.unomaha.peerreview.repository;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.StudentGroup;

public interface StudentGroupRepository extends CrudRepository<StudentGroup, Integer> {

}
