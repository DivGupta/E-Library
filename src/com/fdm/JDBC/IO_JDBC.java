package com.fdm.JDBC;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fdm.JPA.isStorable;

public interface IO_JDBC<T extends isStorable> {

	Connect connect = new Connect();

	public default void connect() {
		connect.con();
	}

	public default void disconnect() {
		connect.discon();
	}

	public default ResultSet setRST(String query) throws SQLException {
		connect.setRst(query);
		return connect.getRst();
	}

	public default PreparedStatement setPS(String query) throws SQLException {
		connect.setPs(query);
		return connect.getPs();
	}

	public default CallableStatement setCS(String query) throws SQLException {
		connect.setCs(query);
		return connect.getCs();
	}

	public default void commit() throws SQLException {
		connect.commit();
	}

	boolean add(T t, String... values) throws SQLException;

	boolean delete(T t) throws SQLException;

	T get(String... values) throws SQLException;

	boolean update(T t, String... input);

}
