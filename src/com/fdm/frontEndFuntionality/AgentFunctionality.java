package com.fdm.frontEndFuntionality;

import java.util.List;

import com.fdm.library.Book;
import com.fdm.library.Library;

public interface AgentFunctionality {

	public default Book getBook(String author, String title) {
		return Library.getBook(title, author);
	}

	public default List<Book> getBook() {
		return Library.getBook();
	}

	public default Book getBook(int bookID) {
		return Library.getBook(bookID);
	}
}
