package com.fdm.JPA;

import com.fdm.library.Book;
import com.fdm.library.BookJPADAO;
import com.fdm.library.Comment;
import com.fdm.library.CommentJPADAO;
import com.fdm.users.Agent;
import com.fdm.users.AgentJPADAO;
import com.fdm.users.User;
import com.fdm.users.UserJPADAO;

public class DAOFactory {
	@SuppressWarnings("unchecked")
	public static <T extends isStorable> IO<T, ?> getStorage(Class<T> clz) {
		if (clz == User.class)
			return (IO<T, ?>) new UserJPADAO();
		else if (clz == Comment.class)
			return (IO<T, ?>) new CommentJPADAO();
		else if (clz == Book.class)
			return (IO<T, ?>) new BookJPADAO();
		else if (clz == Agent.class)
			return (IO<T, ?>) new AgentJPADAO();
		else
			return null;
	}

}
