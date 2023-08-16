package com.fdmgroup.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.service.BookService;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        // Arrange
        List<Book> expectedBooks = Arrays.asList(new Book(), new Book());
        when(bookRepository.findAll()).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.getAllBooks();

        // Assert
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void testGetBooksOfGenre() {
        // Arrange
        BookGenre genre = BookGenre.ACTION;
        List<Book> expectedBooks = Arrays.asList(new Book(), new Book());
        when(bookRepository.findByGenre(genre)).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.getBooksOfGenre(genre);

        // Assert
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void testSearchBooksByTitle() {
        // Arrange
        String title = "Science Fiction";
        List<Book> expectedBooks = Arrays.asList(new Book(), new Book());
        when(bookRepository.findByTitleContainingIgnoreCase(title)).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.searchBooksByTitle(title);

        // Assert
        assertEquals(expectedBooks, actualBooks);
    }
    
    @Test
    public void testSearchBooksByAuthorName() {
        // Arrange
        String authorName = "Isaac";
        List<Book> expectedBooks = Arrays.asList(new Book(), new Book());
        when(bookRepository.findByAuthorContainingIgnoreCase(authorName)).thenReturn(expectedBooks);

        // Act
        List<Book> actualBooks = bookService.searchBooksByAuthorName(authorName);

        // Assert
        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    public void testFindById_BookFound() throws ItemNotFoundException, UserNotFoundException {
        // Arrange
        int bookId = 123;
        Book expectedBook = new Book();
        when(bookRepository.findById(bookId)).thenReturn(expectedBook);

        // Act
        Book actualBook = bookService.findById(bookId);

        // Assert
        assertEquals(expectedBook, actualBook);
    }
    
    @Test(expected = UserNotFoundException.class)
    public void testFindById_BookNotFound() throws ItemNotFoundException, UserNotFoundException {
        // Arrange
        int bookId = 123;
        when(bookRepository.findById(bookId)).thenReturn(null);

        // Act
        bookService.findById(bookId);
    }
    
}
