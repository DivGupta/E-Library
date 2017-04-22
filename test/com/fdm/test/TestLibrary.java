package com.fdm.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.fdm.library.Book;
import com.fdm.library.Comment;
import com.fdm.library.Library;
import com.fdm.users.Agent;
import com.fdm.users.Publisher;
import com.fdm.users.User;

public class TestLibrary {

	@Test
	public void test_GetAllBooks() {
		assertTrue("This will succeed", Library.getBook() instanceof List);
	}

	@Test
	public void test_GetABookWithId() {
		assertTrue(Library.getBook(41) instanceof Book);
		assertTrue(Library.getBook(2321) == null);
	}

	@Test
	public void test_GetABookWithNameAndAuthor() {
		assertTrue(Library.getBook("Ring", "divAuthor") instanceof Book);
		assertTrue(Library.getBook("dasfds", "SDF") == null);
	}

	@Test
	public void test_CreateABookCheckExistsBuyItAndDelete() throws SQLException {
		Book book = new Book("divAuthor", 100, "pinkey", "horror", "the shortest of the series", "testPath");
		Publisher pbsh = new Agent().login("divAuthor", "toad");
		User user = new Agent().login("divUser", "toad");

		Library.newBook(book, pbsh);
		assertTrue(Library.exists(book));
		Library.newBook(book, user);
		Library.deleteBook(book, user);
		Library.deleteBook(book, pbsh);
		assertFalse(Library.exists(book));

	}

	@Test
	public void test_AddRemoveComment() throws SQLException {
		User user = new Agent().login("divUser", "toad");
		Book book = Library.getBook("Ring", "divAuthor");
		Comment comment = new Comment("this book sucked", 2, book, user);
		Library.newBook(book, user);
		Library.newComment(comment, user, book);
		Library.deleteComment(comment, book);
	}

}
