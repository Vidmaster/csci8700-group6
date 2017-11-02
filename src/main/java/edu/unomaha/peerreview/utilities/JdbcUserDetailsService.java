package edu.unomaha.peerreview.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.unomaha.peerreview.model.User;
import edu.unomaha.peerreview.model.User.UserRole;

public class JdbcUserDetailsService implements UserDetailsService {
	private static final String ALL_COLUMNS = "id, username, email, password, enabled";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * This method is required for spring security to lookup the user from the database by username or email address
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("loadUserByUsername(): username=" + username);
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("username", username);
		
		User user = template.queryForObject("SELECT " + ALL_COLUMNS + " FROM users WHERE username=:username OR email=:username", paramMap, new UserRowMapper());
		
		logger.debug("Returning user: " + user);
		return user;
	}
	
	
	public class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int row) throws SQLException {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String email = rs.getString("email");
			String password = rs.getString("password");
			Boolean enabled = rs.getBoolean("enabled");
			UserRole role = UserRole.valueOf(rs.getString("userRole"));
			  
			User user = new User(username, email, password, role, enabled);
			user.setId(id);
			return user;
		}

	}
	
}
