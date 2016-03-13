package com.spring.controller;

import java.util.List;
import java.util.Map;

import com.spring.dao.UserDao;
import com.spring.model.User;
import com.spring.validate.UserValidator;
import com.spring.validate.UserValidatorImpl;

public class UserControllerImpl implements UserController {
	private UserDao userDao;
	private UserValidator validator;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void setUserValidator(UserValidator validator) {
		this.validator = validator;
	}

	public void register(String name, String password, String email) {
		User user = new User(name, password, email);
		Map<String, String> errorMap = validator.validate(user);
		if(errorMap.isEmpty()){
			userDao.insert(user);
			System.out.println("redirect to Ok.jsp");
		} else {
			System.out.println(errorMap);
			System.out.println("redirect to ERROR.jsp");
		}
	}
	
	public List<User> showAll() {
		return userDao.selectAll();
	}
}
