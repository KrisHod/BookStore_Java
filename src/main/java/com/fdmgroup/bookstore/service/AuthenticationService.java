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


	public User authenticate(String username, String password) throws UserNotFoundException {
        if (userRepository.validate(username, password)) {
            return userRepository.findByUsername(username);
        } else {
            throw new UserNotFoundException("User authentication failed.");
        }
    }

	public User findById(int userId) throws UserNotFoundException {
        Object userObject = userRepository.findById(userId);
        if (userObject instanceof User) {
            return (User) userObject;
        } else {
            throw new UserNotFoundException("User not found.");
        }
    }
}
