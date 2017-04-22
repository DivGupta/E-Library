package com.fdm.users;

import java.io.IOException;
import java.sql.SQLException;

public interface validate {

	<Q extends Agent> Q register(String name, String pwd, String email) throws SQLException, IOException;

	<Q extends Agent> Q login(String name, String pwd) throws SQLException;

	boolean logout();

}
