package com.fdmgroup.bookstore.data;

public interface Removeable<T> {
	T delete(T item);
}

