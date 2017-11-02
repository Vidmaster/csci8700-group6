package edu.unomaha.peerreview.repository;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.Authority;

public interface AuthorityRepo extends CrudRepository<Authority, Integer> {

}
