package com.fdmgroup.bookstore.data;

import java.util.List;

import com.fdmgroup.bookstore.model.User;

public interface Searchable<T> {

	T findById(int id);
	List <T> findAll();
	
}
