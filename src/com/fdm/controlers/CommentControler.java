package com.fdm.controlers;

import com.fdm.JPA.QueriesEnums.CommentJpaQueries;
import com.fdm.library.Comment;

public class CommentControler extends controlers<Comment, CommentJpaQueries> {

	public CommentControler() {
		super(Comment.class);
	}

}
