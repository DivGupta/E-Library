package com.fdm.frontEndFuntionality;

import java.util.List;

import com.fdm.library.Book;
import com.fdm.library.Library;
import com.fdm.users.Publisher;

public class PublisherFunctionality implements AgentFunctionality {

	public void createBook(Publisher author, String... args) {
		Book book = new Book(author.getName(), Integer.parseInt(args[0]), args[1], args[2], args[3], args[4]);
		Library.newBook(book, author);
		author.modBib(book, true);
	}

	public void removeBook(Publisher author, Book book) {
		// author.modBib(book, false);
		// for (Comment c : book.getBookComments())
		// book.removeComment(c);
		Library.deleteBook(book, author);
	}

	public List<Book> myBooks(Publisher author) {
		return author.bibliography();
	}

	public void modBook(String title, String author, String... args) {
		Book book = Library.getBook(title, author);
		modBook(book, args);
	}

	public void modBook(Book book, String... args) {
		book.setDesc(args[0]);
		book.setGenre(args[1]);
		book.setLength(Integer.parseInt(args[2]));
		Library.modBook(book);
	}
}
