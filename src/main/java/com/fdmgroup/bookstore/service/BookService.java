package com.fdmgroup.bookstore.service;

import java.util.List;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;

public class BookService {
private BookRepository bookRepository;

public BookService(BookRepository bookRepository) {
	super();
	this.bookRepository = bookRepository;
}

public BookRepository getBookRepository() {
	return bookRepository;
}

public void setBookRepository(BookRepository bookRepository) {
	this.bookRepository = bookRepository;
}

public List<Book> getAllBooks(){
	return bookRepository.findAll();
}

public List<Book> getBooksOfGenre(BookGenre bookGenre){
	return bookRepository.findByGenre(bookGenre);
	
}

public List<Book> searchBooksByTitle(String title){
	return bookRepository.findByTitleContainingIgnoreCase(title);
	
}

public List<Book> searchBooksByAuthorName(String bookAuthorNameToSearch){
	return bookRepository.findByAuthorContainingIgnoreCase(bookAuthorNameToSearch);
	
}

public Book findById(int bookId) throws ItemNotFoundException, UserNotFoundException {
    Object book = bookRepository.findById(bookId);
    if (book  instanceof Book) {
        return (Book) book;
    } else {
        throw new UserNotFoundException("User not found.");
    }
}

}
