package com.fdmgroup.bookstore.data;

import java.util.List;

public interface Persistable<T> {
	T save(T item);

	void saveAll(List<T> items);
}
