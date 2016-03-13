package com.spring.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.spring.model.User;

public class UserControllerTest {
	private UserController userController;
	private EmbeddedDatabase db;
	@Before
	public void setUp() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		this.userController = context.getBean("userController", UserControllerImpl.class);
	}
	
	@Test
	public void registerUser() {
		userController.register("John","Password1","John@jml.com");
		List<User> users = userController.showAll();
		for(User user: users){
			System.out.println("name: "+user.getUsername());
		}
	}
}
