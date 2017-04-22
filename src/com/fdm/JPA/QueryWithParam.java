package com.fdm.JPA;

public class QueryWithParam<Q extends QueriesEnums> {

	Q query;
	Object value;

	public QueryWithParam(Q query, Object... value) {
		super();
		if (query == null)
			throw new IllegalArgumentException("QueryEnum cannot be null");

		this.query = query;

		if (value.length == 1)
			this.value = value[0];
		else
			this.value = value;
	}

	public Q getQuery() {
		return query;
	}

	public Object getValue() {
		return value;
	}

}
