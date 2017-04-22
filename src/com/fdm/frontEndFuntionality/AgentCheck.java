package com.fdm.frontEndFuntionality;

import java.io.IOException;
import java.sql.SQLException;

import com.fdm.users.Agent;
import com.fdm.users.Publisher;
import com.fdm.users.User;

public class AgentCheck {
	private Agent agent = new Agent();

	public AgentCheck() {
		super();
	}

	public <Q extends Agent> Q createUser(String name, String pwd) {
		try {
			return agent.login(name, pwd);
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public <Q extends Agent> Q createUser(String name, String pwd, String email, int type) throws IOException {

		if (Agent.checkExists(name))
			return null;

		try {
			if (type == 1)
				return new User().register(name, pwd, email);
			else if (type == 2)
				return new Publisher().register(name, pwd, email);
			return null;

		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void modUser(Agent agent, String email, String... pwd) {

		if (!email.isEmpty())
			agent.setNewEmail(email);
		if (pwd.length > 0)
			agent.setNewPwd(pwd[0], pwd[1]);
		agent.update();
	}
}
