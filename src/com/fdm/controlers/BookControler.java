package com.fdm.controlers;

import java.util.List;

import com.fdm.JPA.QueriesEnums.BookJpaQueries;
import com.fdm.JPA.QueriesEnums.ProcedureJpaQueries;
import com.fdm.JPA.QueryWithParam;
import com.fdm.library.Book;
import com.fdm.users.Agent;

public class BookControler extends controlers<Book, BookJpaQueries> {

	BookControler() {
		super(Book.class);
	}

	public Book getBook(int id) {
		return dao.readOne(new QueryWithParam<BookJpaQueries>(BookJpaQueries.BYID, id));
	}

	public Book getBook(String title, String author) {
		// TODO Auto-generated method stub
		return dao.readOne(new QueryWithParam<BookJpaQueries>(BookJpaQueries.BYNAME, title, author));
	}

	public List<Book> getBook() {
		return dao.read(new QueryWithParam<BookJpaQueries>(BookJpaQueries.ALL));
	}

	public void updateAgentBook(String type, Book book, Agent agent, int add) {
		if (type.equals("author"))
			dao.storedProcedure(new QueryWithParam<ProcedureJpaQueries>(ProcedureJpaQueries.UPDATEBOOKUSER), "uid",
					String.valueOf(agent.getUserId()), "bid", String.valueOf(book.getId()), "type", 1 + add + "");
		else
			dao.storedProcedure(new QueryWithParam<ProcedureJpaQueries>(ProcedureJpaQueries.UPDATEBOOKUSER), "uid",
					String.valueOf(agent.getUserId()), "bid", String.valueOf(book.getId()), "type", 1 + add + "");
	}

	// public void deleteAgentBook(String type, Book book, Agent agent) {
	// if (type.equals("author"))
	// dao.storedProcedure(new QueryWithParam<ProcedureJpaQueries>(ProcedureJpaQueries.UPDATEBOOKUSER), "uid",
	// String.valueOf(agent.getUserId()), "bid", String.valueOf(book.getId()), "type", "3");
	// else
	// dao.storedProcedure(new QueryWithParam<ProcedureJpaQueries>(ProcedureJpaQueries.UPDATEBOOKUSER), "uid",
	// String.valueOf(agent.getUserId()), "bid", String.valueOf(book.getId()), "type", "4");
	// }

}