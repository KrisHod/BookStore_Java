package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserArrayListRepositoryTest {

	private List<User> users;
	private UserArrayListRepository userRepository;

	@BeforeEach
	public void setUp() {
		users = new ArrayList<>();
		userRepository = new UserArrayListRepository(users);
	}

	@Test
	public void testValidateExistingUser() {
		User user1 = new User(1, "John", "Doe", "john_doe", "password123", "john.doe@example.com", null);
		User user2 = new User(2, "Jane", "Smith", "jane_smith", "password456", "jane.smith@example.com", null);
		users.add(user1);
		users.add(user2);

		assertTrue(userRepository.validate("john_doe", "password123"));
		assertTrue(userRepository.validate("jane_smith", "password456"));
	}

	@Test
	public void testValidateNonExistingUser() {
		assertFalse(userRepository.validate("non_existing", "password123"));
	}

	@Test
	public void testFindByUsernameExistingUser() {
		User user1 = new User(1, "John", "Doe", "john_doe", "password123", "john.doe@example.com", null);
		users.add(user1);

		User foundUser = userRepository.findByUsername("john_doe");
		assertEquals("John", foundUser.getFirstName());
		assertEquals("Doe", foundUser.getLastName());
	}

	@Test
	public void testFindByUsernameNonExistingUser() {
		User foundUser = userRepository.findByUsername("non_existing");
		assertNull(foundUser);
	}

	@Test
	public void testSaveNewUser() {
		User newUser = new User(0, "New", "User", "new_user", "new_password", "new.user@example.com", null);
		User savedUser = userRepository.save(newUser);

		assertEquals(1, savedUser.getUserId());
		assertTrue(users.contains(newUser));
	}

	@Test
	public void testSaveExistingUser() {
		User existingUser = new User(1, "John", "Doe", "john_doe", "password123", "john.doe@example.com", null);
		users.add(existingUser);

		existingUser.setPassword("new_password");
		User savedUser = userRepository.save(existingUser);

		assertEquals("new_password", savedUser.getPassword());
		assertEquals(1, userRepository.getUsers().size());
	}

	@Test
	public void testDeleteUser() {
		User user = new User(1, "John", "Doe", "john_doe", "password123", "john.doe@example.com", null);
		users.add(user);

		userRepository.delete(user);

		assertFalse(users.contains(user));
	}

	@Test
	public void testGenerateID() {
		assertEquals(1, userRepository.generateID());
		assertEquals(2, userRepository.generateID());
	}

	@Test
	public void testFindByIdExistingUser() {
		User user1 = new User(1, "John", "Doe", "john_doe", "password123", "john.doe@example.com", null);
		User user2 = new User(2, "Jane", "Smith", "jane_smith", "password456", "jane.smith@example.com", null);
		users.add(user1);
		users.add(user2);

		User foundUser = userRepository.findById(2);
		assertEquals("Jane", foundUser.getFirstName());
		assertEquals("Smith", foundUser.getLastName());
	}

	@Test
	public void testFindByIdNonExistingUser() {
		User user = userRepository.findById(123);
		assertNull(user);
	}

	@Test
	public void testFindAll() {
		User user1 = new User(1, "John", "Doe", "john_doe", "password123", "john.doe@example.com", null);
		User user2 = new User(2, "Jane", "Smith", "jane_smith", "password456", "jane.smith@example.com", null);
		users.add(user1);
		users.add(user2);

		List<User> allUsers = userRepository.findAll();

		assertEquals(2, allUsers.size());
		assertTrue(allUsers.contains(user1));
		assertTrue(allUsers.contains(user2));
	}

}
