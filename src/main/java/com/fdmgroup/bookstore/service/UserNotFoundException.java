package com.fdmgroup.bookstore.service;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(String message) {
		super(message);
	}
}
