package com.fdmgroup.bookstore.service;

import java.util.List;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

public class OrderingService {
	private OrderRepository orderRepository;

	public OrderingService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	public OrderingService(OrderRepository orderRepository, BookRepository bookRepository,
			UserRepository userRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	public OrderRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order placeOder(Book book, User customer) {
		return null;

	}

	public List<Order> placeOrders(List<Book> books, User customer) {
		return null;

	}

	public List<Order> getOrdersForUser(User user) {
		return null;

	}

	public List<Order> getOrdersForBook(Book book) {
		return null;

	}

}
