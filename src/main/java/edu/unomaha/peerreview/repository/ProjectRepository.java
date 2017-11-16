package edu.unomaha.peerreview.repository;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

}
