package com.fdmgroup.bookstore.data;

import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.model.User;

public class UserArrayListRepository implements UserRepository {
	private List<User> users;
	private int id;

	public UserArrayListRepository() {
		super();
	}

	public UserArrayListRepository(List<User> users) {
		this.users = users;
		this.id = 1;
	}

	public int generateId() {
		return id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean validate(String username, String password) {
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public User findByUsername(String username) {
		for (User user : users) {
			if (user.getUserName().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public User save(User user) {
		if (user.getUserId() == 0) {
			user.setUserId(generateID());
			users.add(user);
		} else {
			User existingUser = findById(user.getUserId());
			if (existingUser != null) {
				existingUser.setUserName(user.getUserName());
				existingUser.setPassword(user.getPassword());
				existingUser.setFirstName(user.getFirstName());
				existingUser.setLastName(user.getLastName());
				existingUser.setEmail(user.getEmail());
				existingUser.setOrders(user.getOrders());
			} else {
				users.add(user);
			}
		}
		return user;
	}

	public void delete(User user) {
		users.remove(user);
	}

	public int generateID() {
		return id++;
	}

	public User findById(int id) {
		for (User user : users) {
			if (user.getUserId() == id) {
				return user;
			}
		}
		return null;
	}

	public List<User> findAll() {
		return new ArrayList<>(users);
	}

}
