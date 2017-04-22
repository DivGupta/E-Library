package com.fdm.users;

import com.fdm.JPA.AbstractJPADAO;
import com.fdm.JPA.QueriesEnums.UserJpaQueries;

public class UserJPADAO extends AbstractJPADAO<User, UserJpaQueries> {

	public UserJPADAO() {
		super(User.class);
	}
}
