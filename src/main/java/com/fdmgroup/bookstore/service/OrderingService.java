package com.fdmgroup.bookstore.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;

public class OrderingService {
	private OrderRepository orderRepository;
	private BookService bookService;
    private AuthenticationService authService;

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
	

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public AuthenticationService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthenticationService authService) {
		this.authService = authService;
	}

	public Order placeOder(Book book, User customer) throws UserNotFoundException, ItemNotFoundException {
        authService.findById(customer.getUserId());
        bookService.findById(book.getItemId());

        Order order = new Order(book, customer.getUserId(), LocalDateTime.now());
        orderRepository.save(order);
        return order;
    }

	public List<Order> placeOrders(List<Book> books, User customer) throws UserNotFoundException, ItemNotFoundException {
        authService.findById(customer.getUserId());
        for (Book book : books) {
            bookService.findById(book.getItemId());
	}
        List<Order> orders = OrderBuilder.buildOrders(books, customer);
        orderRepository.saveAll(orders);
        return orders;
	}
	

	public List<Order> getOrdersForUser(User user) {
		return (List<Order>) orderRepository.findById(user.getUserId());

	}

	public List<Order> getOrdersForBook(Book book) {
		return (List<Order>) orderRepository.findById(book.getItemId());

	}

}
