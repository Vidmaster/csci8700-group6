package edu.unomaha.peerreview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.unomaha.peerreview.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findByUsernameContainingOrEmailContaining (String n, String d);
	
	List<User> findByUsername(String username);
}
