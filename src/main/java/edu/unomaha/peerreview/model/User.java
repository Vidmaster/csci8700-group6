package edu.unomaha.peerreview.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class User implements UserDetails {
	public enum UserRole {
		STUDENT,
		PROFESSOR,
		ADMIN
	}
	
	private static final long serialVersionUID = -1160183497493467630L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column(unique = true)
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private boolean enabled;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	@OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "username",
            targetEntity=Authority.class
    )
	private Collection<Authority> authorities;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}
	
	public User(String username, String email,
				 String password, UserRole role, boolean enabled) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.userRole = role;
		this.enabled = enabled;
	}
	
	public User() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
}
