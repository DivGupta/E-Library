package com.fdm.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fdm.library.Comment;

public class CommentsControler implements IO_JDBC<Comment> {
	PreparedStatement ps;
	ResultSet rst;

	@Override
	public boolean add(Comment t, String... values) throws SQLException {
		// TODO Auto-generated method stub
		connect();

		disconnect();
		return false;
	}

	@Override
	public boolean delete(Comment t) throws SQLException {
		connect();

		disconnect();
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Comment get(String... values) throws SQLException {
		connect();

		disconnect();

		// TODO Auto-generated method stub
		return null;
	}

	public List<Comment> get(int id, String book) throws SQLException {
		// TODO Auto-generated method stub
		connect();

		ps = setPS(Queries.getComments());
		ps.setInt(1, id);
		rst = ps.executeQuery();

		List<Comment> comment = new ArrayList<Comment>();

		// while (rst.next())
		// comment.add(new Comments(rst.getString("text"), rst.getDouble("rating"), ));

		disconnect();

		return comment;
	}

	@Override
	public boolean update(Comment t, String... input) {
		// TODO Auto-generated method stub
		return false;
	}

}
