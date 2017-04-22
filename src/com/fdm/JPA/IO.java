package com.fdm.JPA;

import java.util.List;

import com.fdm.JPA.QueriesEnums.FunctionJpaQueries;
import com.fdm.JPA.QueriesEnums.ProcedureJpaQueries;
import com.fdm.users.Agent;

public interface IO<T extends isStorable, Q extends QueriesEnums> {

	boolean create(T t);

	T readOne(QueryWithParam<Q> query);

	List<T> read(QueryWithParam<Q> query);

	boolean update(T t);

	boolean delete(T t);

	String storedFunction(QueryWithParam<FunctionJpaQueries> queryWithParam, String a, String b);

	String storedFunction(QueryWithParam<FunctionJpaQueries> queryWithParam, String a);

	void storedProcedure(QueryWithParam<ProcedureJpaQueries> query, Agent c, String... args);

	void storedProcedure(QueryWithParam<ProcedureJpaQueries> query, String... args);
}
