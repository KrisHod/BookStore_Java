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
	return null;
	
}

public List<Book> getBooksOfGenre(BookGenre bookGenre){
	return null;
	
}

public List<Book> searchBooksByTitle(String title){
	return null;
	
}

public List<Book> searchBooksByAuthorName(String bookAuthorNameToSearch){
	return null;
	
}

}
