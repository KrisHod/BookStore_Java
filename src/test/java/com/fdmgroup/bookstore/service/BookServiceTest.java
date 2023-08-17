package com.fdmgroup.bookstore.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;

public class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    //created a mock instance of the BookRepository class and pass it to the BookService constructor
    //when the BookService is tested, it will use the mock repository
    @BeforeEach
    public void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> dummyBooks = new ArrayList<>();
        dummyBooks.add(new Book(1, 5.5, "Book 1", "Author 1", BookGenre.ACTION));
        dummyBooks.add(new Book(2, 7, "Book 2", "Author 2", BookGenre.THRILLER));
        
        when(bookRepository.findAll()).thenReturn(dummyBooks);
        
        List<Book> result = bookService.getAllBooks();
        
        assertEquals(dummyBooks, result);
    }

    @Test
    public void testGetBooksOfGenre() {
        List<Book> dummyBooks = new ArrayList<>();
        dummyBooks.add(new Book(1, 7, "Book 1", "Author 1", BookGenre.ACTION));
        dummyBooks.add(new Book(2, 6, "Book 2", "Author 2", BookGenre.THRILLER));
        
        when(bookRepository.findByGenre(BookGenre.ACTION)).thenReturn(dummyBooks);
        
        List<Book> result = bookService.getBooksOfGenre(BookGenre.ACTION);
        
        assertEquals(dummyBooks, result);
    }

    @Test
    public void testSearchBooksByTitle() {
        String title = "Harry Potter";
        
        List<Book> dummyBooks = new ArrayList<>();
        dummyBooks.add(new Book(1, 99, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", BookGenre.ACTION));
        dummyBooks.add(new Book(2, 109, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", BookGenre.ACTION));
        
        when(bookRepository.findByTitleContainingIgnoreCase(title)).thenReturn(dummyBooks);
        
        List<Book> result = bookService.searchBooksByTitle(title);
        
        assertEquals(dummyBooks, result);
    }
    
    @Test
    public void testSearchBooksByAuthorName() {
        String authorName = "J.K. Rowling";
        
        List<Book> dummyBooks = new ArrayList<>();
        dummyBooks.add(new Book(1, 34, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", BookGenre.ACTION));
        dummyBooks.add(new Book(2, 66, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", BookGenre.ACTION));
        
        when(bookRepository.findByAuthorContainingIgnoreCase(authorName)).thenReturn(dummyBooks);
        
        List<Book> result = bookService.searchBooksByAuthorName(authorName);
        
        assertEquals(dummyBooks, result);
    }

    @Test
    public void testFindById() throws ItemNotFoundException, UserNotFoundException {
        int bookId = 1;
        Book dummyBook = new Book(bookId, 8, "Book 1", "Author 1", BookGenre.ACTION);

        when(bookRepository.findById(bookId)).thenReturn(dummyBook);

        Book result = bookService.findById(bookId);

        assertEquals(dummyBook, result);
    }

    @Test
    public void testFindByIdNotFound() {
        int bookId = 1;

        when(bookRepository.findById(bookId)).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> {
            bookService.findById(bookId);
        });
    }
}
