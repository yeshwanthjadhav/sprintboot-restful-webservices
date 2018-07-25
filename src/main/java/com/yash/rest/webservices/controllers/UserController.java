package com.yash.rest.webservices.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yash.rest.webservices.exceptions.UserNotFoundException;
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
	public Resource<User> retrieveUser(@PathVariable int id){
		User user = userDao.findOne(id);
		if(user==null){
			throw new UserNotFoundException("id-"+id);
		}
		
		//"all users", SERVER_PATH + "/users"
		//retrieve all users
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		//return userDao.findOne(id);	
		
		return resource;
	}
	
	@PostMapping("/user")
	public User addUser(@RequestBody User user){
		userDao.save(user);
		return user;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userDao.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable int id){
		User user = userDao.deleteById(id);
		if(user == null){
			throw new UserNotFoundException("id - " + id);
		}
	}
	
}
