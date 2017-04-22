package com.fdm.library;

import com.fdm.JPA.AbstractJPADAO;
import com.fdm.JPA.QueriesEnums.BookJpaQueries;

public class BookJPADAO extends AbstractJPADAO<Book, BookJpaQueries> {

	public BookJPADAO() {
		super(Book.class);
	}
}
