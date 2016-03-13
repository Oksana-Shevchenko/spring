package com.spring.controller;

import java.util.List;

import com.spring.model.User;

public interface UserController {
	public void register(String name, String password, String email);
	public List<User> showAll();
}
