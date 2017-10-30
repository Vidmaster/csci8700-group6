package edu.unomaha.peerreview.database;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.unomaha.peerreview.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int row) throws SQLException {
		int id = rs.getInt("id");
		String username = rs.getString("username");
		String email = rs.getString("email");
		String password = rs.getString("password");
		Boolean enabled = rs.getBoolean("enabled");
		  
		return new User(id, username, email, password, enabled);
	}

}
