package com.fdmgroup.bookstore.data;

import java.util.List;

import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;

public interface BookRepository<T> extends Removeable<T>, Persistable<T>, Searchable<T>{

	List<Book> findByGenre(BookGenre bookGenre);

	List<Book> findByTitleContainingIgnoreCase(String title);

	List<Book> findByAuthorContainingIgnoreCase(String bookAuthorNameToSearch);


}
