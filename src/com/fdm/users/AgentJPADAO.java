package com.fdm.users;

import com.fdm.JPA.AbstractJPADAO;
import com.fdm.JPA.QueriesEnums.AgentJpaQueries;

public class AgentJPADAO extends AbstractJPADAO<Agent, AgentJpaQueries> {

	public AgentJPADAO() {
		super(Agent.class);
	}

}
