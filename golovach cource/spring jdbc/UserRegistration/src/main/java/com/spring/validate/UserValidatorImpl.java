package com.spring.validate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spring.model.User;

public class UserValidatorImpl implements UserValidator {
	private String emailPattern;
	private int minLoginLength;
	private int maxLoginLength;
	private int minPasswordLength;
	private int maxPasswordLength;

	public void setEmailPattern(String emailPattern){
		this.emailPattern = emailPattern;
	}
	
	public void setMinLoginLength(int minLoginLength){
		this.minLoginLength = minLoginLength;
	}
	
	public void setMaxLoginLength(int maxLoginLength){
		this.maxLoginLength = maxLoginLength;
	}
	
	public void setMinPasswordLength(int minPasswordLength){
		this.minPasswordLength = minPasswordLength;
	}
	
	public void setMaxPasswordLength(int maxPasswordLength){
		this.maxPasswordLength = maxPasswordLength;
	}
	
	public Map<String, String> validate(User user) {
		HashMap<String, String> errorMap = new HashMap<String, String>();
		validateEmail(user.getEmail(), errorMap);
		validateName(user.getUsername(), errorMap);
		validatePassword(user.getPassword(), errorMap);
		return errorMap;
	}
	
	private void validateEmail(String email, HashMap<String, String> errorMap) {
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			errorMap.put("email", "Bad email");
		}
	}
	
	private void validateName(String name, HashMap<String, String> errorMap) {
		if(name==null || name.trim().isEmpty()){
			errorMap.put("name", "Bad name: empty");
			return;
		}
		if(name.length() < minLoginLength){
			errorMap.put("name", "Bad name: too short");
			return;
		}
		if(name.length() > maxLoginLength){
			errorMap.put("name", "Bad name: too long");
			return;
		}
	}
	
	private void validatePassword(String password, HashMap<String, String> errorMap) {
		if(password==null || password.trim().isEmpty()){
			errorMap.put("password", "Bad password: empty");
			return;
		}
		if(password.length() < minPasswordLength){
			errorMap.put("password", "Bad password: too short");
			return;
		}
		if(password.length() > maxPasswordLength){
			errorMap.put("password", "Bad password: too long");
			return;
		}
	}

}
