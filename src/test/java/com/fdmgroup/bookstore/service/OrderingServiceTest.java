package com.fdmgroup.bookstore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;
import com.fdmgroup.bookstore.service.BookService;
import com.fdmgroup.bookstore.service.OrderingService;
import com.fdmgroup.bookstore.service.UserNotFoundException;
import com.fdmgroup.bookstore.service.ItemNotFoundException;

public class OrderingServiceTest {

    private OrderRepository orderRepository;
    private BookService bookService;
    private AuthenticationService authService;
    private OrderingService orderingService;

    @BeforeEach
    public void setUp() {
        orderRepository = mock(OrderRepository.class);
        bookService = mock(BookService.class);
        authService = mock(AuthenticationService.class);

        orderingService = new OrderingService(orderRepository);
        orderingService.setBookService(bookService);
        orderingService.setAuthService(authService);
    }

    @Test
    public void testPlaceOrder() throws UserNotFoundException, ItemNotFoundException {
        int userId = 1;
        int bookId = 2;
        User dummyUser = new User(userId, "john.doe", "secretpassword");
        Book dummyBook = new Book(bookId, 45.7, "Book Title", "Author Name", BookGenre.ACTION);

        when(authService.findById(userId)).thenReturn(dummyUser);
        when(bookService.findById(bookId)).thenReturn(dummyBook);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderingService.placeOrder(dummyBook, dummyUser);

        assertEquals(dummyBook, result.getBookOrdered());
        assertEquals(userId, result.getUserId());
    }

    @Test
    public void testPlaceOrders() throws UserNotFoundException, ItemNotFoundException {
        int userId = 1;
        User dummyUser = new User(userId, "john.doe", "secretpassword");
        Book dummyBook1 = new Book(2, 99.8, "Book Title 1", "Author Name 1", BookGenre.ACTION);
        Book dummyBook2 = new Book(3, 67, "Book Title 2", "Author Name 2", BookGenre.CRIME);
        List<Book> dummyBooks = new ArrayList<>();
        dummyBooks.add(dummyBook1);
        dummyBooks.add(dummyBook2);

        when(authService.findById(userId)).thenReturn(dummyUser);
        when(bookService.findById(2)).thenReturn(dummyBook1);
        when(bookService.findById(3)).thenReturn(dummyBook2);

        List<Order> orders = orderingService.placeOrders(dummyBooks, dummyUser);

        assertEquals(dummyBooks.size(), orders.size());
        for (Order order : orders) {
            assertEquals(userId, order.getUserId());
        }
    }

    @Test
    public void testGetOrdersForUser() {
        int userId = 1;
        User dummyUser = new User(userId, "john.doe", "secretpassword");

        List<Order> dummyOrders = new ArrayList<>();
        dummyOrders.add(new Order(new Book(), userId, LocalDateTime.now()));
        dummyOrders.add(new Order(new Book(), userId, LocalDateTime.now()));

        when(orderRepository.findById(userId)).thenReturn(dummyOrders);

        List<Order> result = orderingService.getOrdersForUser(dummyUser);

        assertIterableEquals(dummyOrders, result);
    }
    
    @Test
    public void testGetOrdersForBook() {
        int bookId = 2;
        Book dummyBook = new Book(bookId, 55, "Book Title", "Author Name", BookGenre.ACTION);
        
        List<Order> dummyOrders = new ArrayList<>();
        dummyOrders.add(new Order(dummyBook, 1, LocalDateTime.now()));
        dummyOrders.add(new Order(dummyBook, 2, LocalDateTime.now()));
        
        when(orderRepository.findById(bookId)).thenReturn(dummyOrders);
        
        List<Order> result = orderingService.getOrdersForBook(dummyBook);
        
        assertEquals(dummyOrders, result);
    }

}

