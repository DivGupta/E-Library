package com.fdm.library;

import com.fdm.JPA.AbstractJPADAO;
import com.fdm.JPA.QueriesEnums.CommentJpaQueries;

public class CommentJPADAO extends AbstractJPADAO<Comment, CommentJpaQueries> {

	public CommentJPADAO() {
		super(Comment.class);
	}
}
