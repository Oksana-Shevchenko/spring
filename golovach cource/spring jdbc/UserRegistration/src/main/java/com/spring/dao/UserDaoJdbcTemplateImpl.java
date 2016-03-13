package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import com.spring.model.User;

@Repository
public class UserDaoJdbcTemplateImpl implements UserDao {
	private JdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = {Throwable.class})
	public void insert(User user) {
		jdbc.update("INSERT INTO USERS (username, password, email) VALUES (?,?,?)", user.getUsername(), user.getPassword(), user.getEmail());
	}
	
	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public List<User> selectAll() {
		return jdbc.query("SELECT username, password, email FROM USERS", userMapper);
	}
	
	private static final RowMapper<User> userMapper = new RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email") );
            return user;
        }
    };
}
