package com.fdm.JPA;

public interface QueriesEnums {

	public String getQuery();

	public String getParamName();

	enum UserJpaQueries implements QueriesEnums {
		BYUSERNAME("select u from User u where u.a=:a", "a"), BYUSERID("select u from User u where u.id=:id",
				"id"), ALL("SELECT u from User u", null);

		private final String query, paramName;

		@Override
		public String getQuery() {
			return query;
		}

		@Override
		public String getParamName() {
			return paramName;
		}

		private UserJpaQueries(String query, String paramName) {
			this.query = query;
			this.paramName = paramName;
		}
	}

	enum BookJpaQueries implements QueriesEnums {
		BYID("select w from Book w where w.bookId = :id", "id"), ALL("select b from Book b",
				null), BYNAME("select w from Book w where w.title = :title and w.author = :author", "title:author");

		private final String query, paramName;

		@Override
		public String getQuery() {
			return query;
		}

		@Override
		public String getParamName() {
			return paramName;
		}

		private BookJpaQueries(String query, String paramName) {
			this.query = query;
			this.paramName = paramName;
		}
	}

	enum CommentJpaQueries implements QueriesEnums {
		BYUSERNAME("select w from Comments w where w.a = :a", "ar"), ALL("select w from comments w", null);

		private final String query, paramName;

		@Override
		public String getQuery() {
			return query;
		}

		@Override
		public String getParamName() {
			return paramName;
		}

		private CommentJpaQueries(String query, String paramName) {
			this.query = query;
			this.paramName = paramName;
		}
	}

	enum AgentJpaQueries implements QueriesEnums {
		BYUSERNAME("select u from Agent u where u.name=:name", "name"), ALL("SELECT u from Agent u", null);

		private final String query, paramName;

		@Override
		public String getQuery() {
			return query;
		}

		@Override
		public String getParamName() {
			return paramName;
		}

		private AgentJpaQueries(String query, String paramName) {
			this.query = query;
			this.paramName = paramName;
		}
	}

	enum FunctionJpaQueries implements QueriesEnums {
		VERIFY("SELECT VERIFY_PASSWORD(?1, ?2) FROM DUAL", "name"), SALT("SELECT GET_SALT(?1) FROM DUAL", "name");

		private final String query, paramName;

		@Override
		public String getQuery() {
			return query;
		}

		@Override
		public String getParamName() {
			return paramName;
		}

		private FunctionJpaQueries(String query, String paramName) {
			this.query = query;
			this.paramName = paramName;
		}
	}

	enum ProcedureJpaQueries implements QueriesEnums {
		NEWPWD("NEW_PWD", null), SETPWD("SET_PWD", null), UPDATEBOOKUSER("BOOK_USER", null);

		private final String query, paramName;

		@Override
		public String getQuery() {
			return query;
		}

		@Override
		public String getParamName() {
			return paramName;
		}

		private ProcedureJpaQueries(String query, String paramName) {
			this.query = query;
			this.paramName = paramName;
		}
	}
}
