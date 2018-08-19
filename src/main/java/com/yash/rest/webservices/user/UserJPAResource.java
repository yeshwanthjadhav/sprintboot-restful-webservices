package com.yash.rest.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

@RestController
public class UserJPAResource {

	@Autowired
	UserDaoService userDao;
	
	@Autowired
	UserRepository userRepository;
	
	public UserJPAResource() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("/jpa/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id-"+id);
		}
		
		//"all users", SERVER_PATH + "/users"
		//retrieve all users
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		//return userDao.findOne(id);	
		
		return resource;
	}
	
	@PostMapping("/jpa/user")
	public User addUser(@RequestBody User user){
		userDao.save(user);
		return user;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
		User savedUser = userDao.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/user/{id}")
	public void deleteUser(@PathVariable int id){
		User user = userDao.deleteById(id);
		if(user == null){
			throw new UserNotFoundException("id - " + id);
		}
	}
	
}
