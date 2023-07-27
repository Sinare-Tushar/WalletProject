package com.example.spring.datajpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")

public class User implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "USER_SEQUENCE", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "USER_SEQUENCE")
	private long id;

	@Column(name = "username")
	@NonNull
	private String username;

	@Column(name = "password")
	@NonNull
	private String password;

	@Column(name = "email_id")
	@NonNull
	private String email;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email_id=" + email + "]";
	}

	public User(String username, String password, String email) {
		
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
