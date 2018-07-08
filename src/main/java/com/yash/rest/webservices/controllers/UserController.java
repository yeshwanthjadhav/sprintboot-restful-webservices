package com.yash.rest.webservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yash.rest.webservices.user.User;
import com.yash.rest.webservices.user.UserDaoService;

@RestController
public class UserController {

	@Autowired
	UserDaoService userDao;
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userDao.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id){
		return userDao.findOne(id);	
	}
	
}
