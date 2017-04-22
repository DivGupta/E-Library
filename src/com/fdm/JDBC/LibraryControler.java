package com.fdm.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fdm.library.Book;

public class LibraryControler implements IO_JDBC<Book> {
	PreparedStatement ps;
	ResultSet rst;
	String path = "H:\\testFolder";

	@Override
	public boolean add(Book t, String... value) throws SQLException {

		connect();

		rst = setRST(Queries.bookSeq());

		rst.next();
		int index = rst.getInt(1);
		String newPath = path + "\\" + t.getAuthor() + "\\" + t.getTitle();

		ps = setPS(Queries.insertBook());
		ps.setInt(1, index);
		ps.setInt(2, t.getLength());
		ps.setString(3, t.getGenre());
		ps.setString(4, t.getTitle());
		ps.setLong(5, t.getPrice());
		ps.setString(6, newPath);

		ps.addBatch();
		ps.executeBatch();

		commit();
		disconnect();
		return false;
	}

	@Override
	public boolean delete(Book t) {
		connect();

		disconnect();
		return false;
	}

	@Override
	public Book get(String... input) {
		connect();

		disconnect();
		return null;
	}

	public Book get(int ind) {
		connect();

		disconnect();
		return null;
	}

	public ArrayList<Book> get() throws SQLException {
		connect();

		rst = setRST(Queries.getBooks());
		ArrayList<Book> allBooks = new ArrayList<>();

		while (rst.next())
			// allBooks.add(new Book(rst.getString("USER_NAME"), rst.getInt("length"), rst.getString("title"),
			// rst.getString("genre"), rst.getInt("price")));

			disconnect();
		return allBooks;

	}

	@Override
	public boolean update(Book t, String... input) {
		connect();

		disconnect();
		return false;
	}

}
