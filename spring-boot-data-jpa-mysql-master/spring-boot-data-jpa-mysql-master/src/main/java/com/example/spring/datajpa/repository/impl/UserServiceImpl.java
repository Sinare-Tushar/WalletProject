package com.example.spring.datajpa.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.service.IUserService;
import com.example.spring.datajpa.model.User;
import com.example.spring.datajpa.repository.UserServiceRepository;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	UserServiceRepository userServiceRepository ;
	 
	@Override
	public User createUser(User user) {
		
		return userServiceRepository.save(user);
	}

	@Override
	public String login(String username, String password) {
		
		String SUCCESS_MESSAGE = "USER IS VALID ";
		
		String FAILURE_MESSAGE  = "USER IS NOT VALID ";
		
		if(userServiceRepository.findByUsernameAndPassword(username, password).isPresent()) {
			
			return SUCCESS_MESSAGE;
		}
		
		return FAILURE_MESSAGE;
				
	}
	@Override
	public List<User> allUserRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> createBatchUser(List<User> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll(List<User> ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAllUsers(List<User> users) {
		// TODO Auto-generated method stub
		return false;
	}

	

	
}
