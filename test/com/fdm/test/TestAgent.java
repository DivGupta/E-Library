package com.fdm.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.fdm.users.Agent;

public class TestAgent {

	@Test
	public void test_RegisterWithValidUser() throws SQLException, IOException {

		Agent agent = new Agent().register("test", "blarg", "test@test.test");

		assertEquals(agent.getName(), "test");
		assertEquals(agent.getEmail(), "test@test.test");

		assertEquals(Agent.checkExists("test"), true);
		assertEquals(Agent.checkExists("test1"), false);

		assertEquals(agent.login("test", "blarg"), agent);
	}

	@Test(expected = IOException.class)
	public void test_RegisterWithInvalidUserName() throws IOException, SQLException {
		Agent agent = new Agent();
		agent.register(null, "blarg", "test@test.test");
	}

	@Test(expected = IOException.class)
	public void test_RegisterWithInvalidUserPwd() throws IOException, SQLException {
		Agent agent = new Agent();
		agent.register("test", "blarg", null);
	}

	@Test(expected = IOException.class)
	public void test_RegisterWithInvalidUserEmail() throws IOException, SQLException {
		Agent agent = new Agent();
		agent.register("test", null, "test@test.test");
	}

	public void test_CheckAgentLoginAndModifications() throws SQLException {
		Agent agent = null;
		agent = agent.login("test", "blarg");
	}

}
