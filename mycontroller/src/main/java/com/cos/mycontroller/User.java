package com.cos.mycontroller;

import org.springframework.web.bind.annotation.PostMapping;

public class User {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		System.out.println("setUsername() 실행됨");
		this.username=username;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		System.out.println("setPassword() 실행됨");
		this.password=password;
	}
	
	@PostMapping("/test/post/object")
	public User testPostObject(User user) {
		return user;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
}
