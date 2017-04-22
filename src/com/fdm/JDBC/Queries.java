package com.fdm.JDBC;

public class Queries {

	public static String getUser() {
		return "Select * from USERS where USER_NAME = ? ";
	}

	public static String getBook() {
		return "Select * from books where title = ? ";
	}

	public static String getBooks() {
		return "Select * from books b join author_books ab on b.book_id = ab.book_id join users u on u.user_id = ab.AUTHOR_ID";
	}

	public static String getComments() {
		return "Select * from comments c join user_books ub on ub.user_id = c.user_id join users u on u.user_id = c.user_id"
				+ " where comments.book_id = ? ";
	}

	public static String insertUser() {
		return "INSERT into users values (?, ?, ?, ?, ?, ?)";
	}

	public static String insertBook() {
		return "INSERT into books values (?, ?, ?, ?, ?, ?)";
	}

	public static String userSeq() {
		return "select SEQ_USERS_ID.NEXTVAL from dual";
	}

	public static String bookSeq() {
		return "select SEQ_BOOKS_ID.NEXTVAL from dual";
	}

	public static String deleteUser() {
		return "DELETE FROM users WHERE ?=?";
	}

	public static String deleteBook() {
		return "DELETE FROM books WHERE ?=?";
	}

}
