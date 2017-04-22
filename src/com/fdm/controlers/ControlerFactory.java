package com.fdm.controlers;

import com.fdm.JPA.isStorable;
import com.fdm.library.Book;
import com.fdm.users.Agent;
import com.fdm.users.Publisher;

public class ControlerFactory {

	public static AgentControler getAgentControler() {
		return new AgentControler();
	}

	public static BookControler getBookControler() {
		return new BookControler();
	}

	public static CommentControler getCommentControler() {
		return new CommentControler();
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	public static <T extends isStorable> controlers<T, ?> getControler(Class<T> clz) {

		if (clz == Agent.class)
			return (controlers<T, ?>) new AgentControler();
		else if (clz == Book.class)
			return (controlers<T, ?>) new BookControler();
		else if (clz == Publisher.class)
			return (controlers<T, ?>) new CommentControler();

		return null;

	}

}
