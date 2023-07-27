package com.example.spring.datajpa.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.spring.datajpa.model.User;

@EnableJpaRepositories
public interface UserServiceRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
	Optional<User> findByUsernameAndPassword(String username, String password);


}
