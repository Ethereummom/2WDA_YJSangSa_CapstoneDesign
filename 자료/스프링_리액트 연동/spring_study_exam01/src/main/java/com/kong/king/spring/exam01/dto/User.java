package com.kong.king.spring.exam01.dto;

import lombok.Getter;

@Getter
public class User {
	int id;
	String username;
	String password;
	String email;
	
	public User(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
