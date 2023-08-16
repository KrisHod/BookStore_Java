package com.fdmgroup.bookstore.service;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.User;

public class AuthenticationService {
	private UserRepository userRepository;

	public AuthenticationService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	

	public UserRepository getUserRepository() {
		return userRepository;
	}


	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public User authenticate(String username, String password) {
		return null;

	}
}
