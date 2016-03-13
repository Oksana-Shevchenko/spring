package com.spring.validate;

import java.util.Map;

import com.spring.model.User;

public interface UserValidator {
	public Map<String, String> validate(User user);
}
