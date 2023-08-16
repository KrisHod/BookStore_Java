package com.fdmgroup.bookstore.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

public class OrderBuilder {

	public static List<Order> buildOrders(List<Book> books, User customer) {
		List<Order> orders = new ArrayList<>();

		for (Book book : books) {
			Order order = new Order(book, customer.getUserId(), LocalDateTime.now());
			orders.add(order);
		}

		return orders;
	}
}
