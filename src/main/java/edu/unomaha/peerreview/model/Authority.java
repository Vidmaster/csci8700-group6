package edu.unomaha.peerreview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = -6613162552418982990L;

	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;
	@Column
	private String authority;

	@Column
	private String username;
	
	
	public Authority() {
		
	}
	
	public Authority(String username, String authority) {
		this.username = username;
		this.authority = authority;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
