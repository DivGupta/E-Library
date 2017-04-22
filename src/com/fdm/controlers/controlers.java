package com.fdm.controlers;

import com.fdm.JPA.DAOFactory;
import com.fdm.JPA.IO;
import com.fdm.JPA.QueriesEnums;
import com.fdm.JPA.isStorable;

public abstract class controlers<T extends isStorable, Q extends QueriesEnums> {

	protected IO<T, Q> dao;

	@SuppressWarnings("unchecked")
	public controlers(Class<T> input) {
		dao = (IO<T, Q>) DAOFactory.getStorage(input);
	}

	public void write(T t) {
		dao.create(t);
	}

	public void update(T t) {
		dao.update(t);
	}

	public void delete(T t) {
		dao.delete(t);
	}
}