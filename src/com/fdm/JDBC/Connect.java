package com.fdm.JDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fdm.JPA.AllConnections;

public class Connect implements AllConnections {

	private String url = "jdbc:oracle:thin:MEEEE/toad@localhost:1521:xe";
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rst;
	private CallableStatement cs;

	static {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean con() {
		try {
			this.conn = DriverManager.getConnection(url);
			this.st = null;
			this.rst = null;
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean discon() {
		try {
			this.conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setRst(String query) throws SQLException {
		this.st = conn.createStatement();
		this.rst = this.st.executeQuery(query);
	}

	public String getUrl() {
		return url;
	}

	public Statement getSt() {
		return st;
	}

	public ResultSet getRst() {
		return rst;
	}

	public void setPs(String query) throws SQLException {
		conn.setAutoCommit(false);
		this.ps = conn.prepareStatement(query);
	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void commit() throws SQLException {
		conn.commit();
	}

	public CallableStatement getCs() {
		return cs;
	}

	public void setCs(String query) throws SQLException {
		this.cs = conn.prepareCall(query);
	}

}
