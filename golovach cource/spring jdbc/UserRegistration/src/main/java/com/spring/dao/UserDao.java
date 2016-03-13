package com.spring.dao;

import java.util.List;

import com.spring.model.User;

public interface UserDao {
	public void insert(User user);
	public List<User> selectAll();
}
