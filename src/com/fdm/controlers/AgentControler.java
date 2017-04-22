package com.fdm.controlers;

import com.fdm.JPA.QueryWithParam;
import com.fdm.JPA.QueriesEnums.AgentJpaQueries;
import com.fdm.JPA.QueriesEnums.FunctionJpaQueries;
import com.fdm.JPA.QueriesEnums.ProcedureJpaQueries;
import com.fdm.users.Agent;
import com.fdm.users.encrypt;

public class AgentControler extends controlers<Agent, AgentJpaQueries> {

	AgentControler() {
		super(Agent.class);
	}

	public boolean checkExists(String input) {
		Agent a = dao.readOne(new QueryWithParam<AgentJpaQueries>(AgentJpaQueries.BYUSERNAME, input));
		if (a != null)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public <Q extends Agent> Q login(String username, String pwd) {
		String salt = dao.storedFunction(new QueryWithParam<FunctionJpaQueries>(FunctionJpaQueries.SALT), username);

		String status = dao.storedFunction(new QueryWithParam<FunctionJpaQueries>(FunctionJpaQueries.VERIFY), username,
				encrypt.hash(pwd, salt));
		Q a = null;
		if (status.contains("true"))
			a = (Q) dao.readOne(new QueryWithParam<AgentJpaQueries>(AgentJpaQueries.BYUSERNAME, username));

		return a;
	}

	@SuppressWarnings("unchecked")
	public void register(String pwd, String salt, Agent agent) {
		dao.create(agent);
		dao.storedProcedure(new QueryWithParam<ProcedureJpaQueries>(ProcedureJpaQueries.SETPWD), agent, "user", "setP",
				pwd, "sa", salt);

	}

	public void newPWD(String pwd, String newpwd, Agent agent) {
		String salt = dao.storedFunction(new QueryWithParam<FunctionJpaQueries>(FunctionJpaQueries.SALT),
				agent.getName());

		QueryWithParam<ProcedureJpaQueries> query = new QueryWithParam<ProcedureJpaQueries>(ProcedureJpaQueries.NEWPWD);
		dao.storedProcedure(query, agent, "user", "pwd", encrypt.hash(pwd, salt), "newpwd", encrypt.hash(newpwd, salt));
	}

	// public static void main(String[] args) {
	// AgentControler ac = new AgentControler();
	// if (!ac.checkExists("div1"))
	// System.out.println("worked");
	// }

}
