package com.fdm.users;

import com.fdm.JPA.AbstractJPADAO;
import com.fdm.JPA.QueriesEnums.UserJpaQueries;

public class PublisherJPADAO extends AbstractJPADAO<Publisher, UserJpaQueries> {

	public PublisherJPADAO() {
		super(Publisher.class);
	}
}
