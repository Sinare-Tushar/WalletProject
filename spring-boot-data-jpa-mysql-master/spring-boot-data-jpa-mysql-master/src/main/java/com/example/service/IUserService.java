package com.example.service;
import java.util.List;

import com.example.spring.datajpa.model.User;

public interface IUserService{

	User createUser(User user);

	List<User> allUserRecord();
	
	User getUserById(long id);
	
	List<User> createBatchUser(List<User> users);
	
	boolean delete(long id);
	
	boolean deleteAll(List<User> ids);
	
	boolean updateUser(User id);
	
	boolean updateAllUsers (List<User> users);
	
	
	String login(String username, String password);
}
