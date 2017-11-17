package edu.unomaha.peerreview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.unomaha.peerreview.model.User;
import edu.unomaha.peerreview.model.User.UserRole;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findByUsernameContainingOrEmailContaining (String n, String d);
	
	List<User> findByUsername(String username);
	
	List<User> findByUserRole(UserRole role);
	
}
