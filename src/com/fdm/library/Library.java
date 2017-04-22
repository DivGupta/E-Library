package com.fdm.library;

import java.util.List;

import com.fdm.controlers.ControlerFactory;
import com.fdm.users.Agent;
import com.fdm.users.User;

public class Library {

	// private static LibraryControler LC = new LibraryControler();

	// public static void main(String[] args) throws SQLException {
	// LC.add(new Book(null, 0, null, null));
	// List<Book> temp = LC.get();
	// System.out.println(temp);

	// public static Book modify() {
	// return null;
	// }
	//
	// public static Book retrieve(int id) {
	// return null;
	// }
	//
	// public static Book retrieve(String title) {
	// return LC.get(title);
	// }
	//
	// public static Book[] retrieve() {
	// return null;
	// }
	//
	// public static boolean add(String author, int length, String title, String genre, long price) throws SQLException {
	// Book book = new Book(author, length, title, genre, price);
	// LC.add(book);
	// return false;
	// }
	//
	// public static boolean add(String author, int length, String title, String genre) throws SQLException {
	// Book book = new Book(author, length, title, genre);
	// LC.add(book);
	// return false;
	// }
	// add("div", 123, "the book", "horror");
	// }

	public static List<Book> getBook() {
		return ControlerFactory.getBookControler().getBook();
	}

	public static Book getBook(int bookID) {
		return ControlerFactory.getBookControler().getBook(bookID);
	}

	public static Book getBook(String title, String author) {
		return ControlerFactory.getBookControler().getBook(title, author);
	}

	public static boolean exists(Book book) {
		Book a = getBook(book.getTitle(), book.getAuthor());
		if (a != null)
			return true;
		return false;
	}

	public static void newBook(Book book, Agent agent) {
		ControlerFactory.getBookControler().write(book);
		ControlerFactory.getBookControler().updateAgentBook("author", book, agent, 0);
	}

	public static void newBook(Book book, User user) {
		ControlerFactory.getBookControler().updateAgentBook("user", book, user, 0);
	}

	public static void deleteBook(Book book, Agent agent) {
		ControlerFactory.getBookControler().updateAgentBook("author", book, agent, 2);
		ControlerFactory.getBookControler().delete(book);
	}

	public static void deleteBook(Book book, User user) {
		ControlerFactory.getBookControler().updateAgentBook("user", book, user, 2);
	}

	public static void modBook(Book book) {
		ControlerFactory.getBookControler().update(book);
	}

	public static boolean newComment(Comment comment, User user, Book book) {
		if (user.isOwned(book)) {
			book.addComment(comment);
			return true;
		}
		return false;

	}

	public static void deleteComment(Comment comment, Book book) {
		book.removeComment(comment);
	}

}
