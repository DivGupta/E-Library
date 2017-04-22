package com.fdm.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fdm.users.User;
import com.fdm.users.encrypt;

public class UserControler implements IO_JDBC<User> {
	PreparedStatement ps;
	ResultSet rst;

	@Override
	public boolean add(User t, String... input) throws SQLException {

		connect();

		String pwd = input[0];
		rst = setRST(Queries.userSeq());

		rst.next();
		int index = rst.getInt(1);

		ps = setPS(Queries.insertUser());
		ps.setInt(1, index);
		ps.setString(2, t.getName());
		ps.setString(3, pwd);
		ps.setString(4, t.getEmail());
		ps.setInt(5, index);

		ps.addBatch();
		ps.executeBatch();

		disconnect();
		return false;
	}

	@Override
	public boolean delete(User t) {
		connect();

		disconnect();
		return false;
	}

	@Override
	public User get(String... input) throws SQLException {
		User user = null;

		connect();

		String userName = input[0];
		String pwd = input[1];

		ps = setPS(Queries.getUser());
		ps.setString(1, userName);
		rst = ps.executeQuery();

		while (rst.next())
			if (encrypt.check(pwd, rst.getString("pwd"))) {
				int writer = rst.getInt("IS_WRITER") == 1 ? 1 : 0;
				user = new User(rst.getString("user_name"), rst.getString("email"));
			}
		disconnect();
		return user;
	}

	@Override
	public boolean update(User t, String... input) {
		connect();

		disconnect();
		return false;
	}

}
