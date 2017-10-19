package edu.unomaha.peerreview.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.unomaha.peerreview.database.JdbcUserDao;
import edu.unomaha.peerreview.database.UserDao;

@Configuration
public class PeerReviewConfiguration {

	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.username(username)
				.password(password)
				.url(url)
				.build();
	}
	
	@Bean
	public UserDao userDao() {
		return new JdbcUserDao();
	}
	
}
