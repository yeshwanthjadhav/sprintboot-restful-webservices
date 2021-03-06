package com.yash.rest.webservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository  //you can also use @Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	
	static{
		users.add(new User(1, "Yesh", new Date()));
		users.add(new User(2, "Suraj", new Date()));
		users.add(new User(3, "Parimal", new Date()));
	}

	public UserDaoService() {
		// TODO Auto-generated constructor stub
	}
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User save(User user){
		if(user.getId()==null){
			user.setId(userCount++);
		}
		users.add(user);
		return user;		
	}
		
	public User findOne(int id){
		for(User user:users){
			if(user.getId() == id){
				return user;
			}		
		}
		return null;
	}

	public User deleteById(int id){
		Iterator<User> iterator = users.iterator();
			while(iterator.hasNext()){
				User user  = iterator.next();				 
				if(user.getId() == id){
					iterator.remove();
					return user;
				}		
		}
		return null;
	}

}
