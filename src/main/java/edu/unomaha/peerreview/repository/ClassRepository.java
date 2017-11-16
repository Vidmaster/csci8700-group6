package edu.unomaha.peerreview.repository;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.Class;

public interface ClassRepository extends CrudRepository<Class, Integer> {

}
