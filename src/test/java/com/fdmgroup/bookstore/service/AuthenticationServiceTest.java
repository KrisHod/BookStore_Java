package com.fdmgroup.bookstore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.UserRepository;
import com.fdmgroup.bookstore.model.User;

public class AuthenticationServiceTest {

    private UserRepository userRepository;
    private AuthenticationService authService;

    //set up a mock UserRepository
    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        authService = new AuthenticationService(userRepository);
    }

    @Test
    public void testAuthenticateSuccessful() throws UserNotFoundException {
        String username = "john.doe";
        String password = "secretpassword";
        User dummyUser = new User(1, username, password);

        //defined the behavior of the mock repository using when and thenReturn methods
        when(userRepository.validate(username, password)).thenReturn(true);
        when(userRepository.findByUsername(username)).thenReturn(dummyUser);

        User result = authService.authenticate(username, password);

        assertEquals(dummyUser, result);
    }

    @Test
    public void testAuthenticateFailed() {
        String username = "invaliduser";
        String password = "wrongpassword";

        when(userRepository.validate(username, password)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> {
            authService.authenticate(username, password);
        });
    }

    @Test
    public void testFindById() throws UserNotFoundException {
        int userId = 1;
        User dummyUser = new User(userId, "john.doe", "secretpassword");

        when(userRepository.findById(userId)).thenReturn(dummyUser);

        User result = authService.findById(userId);

        assertEquals(dummyUser, result);
    }

    @Test
    public void testFindByIdNotFound() {
        int userId = 1;

        when(userRepository.findById(userId)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            authService.findById(userId);
        });
    }
}
