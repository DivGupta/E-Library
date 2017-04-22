package com.fdm.JPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import com.fdm.JPA.QueriesEnums.FunctionJpaQueries;
import com.fdm.JPA.QueriesEnums.ProcedureJpaQueries;
import com.fdm.users.Agent;

public abstract class AbstractJPADAO<T extends isStorable, Q extends QueriesEnums> implements IO<T, Q> {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("E-Library");
	protected static EntityManager em = emf.createEntityManager();

	private Class<T> entityType;

	public AbstractJPADAO(Class<T> clazz) {
		this.entityType = clazz;
	}

	public static void closeResources() {
		if (em != null)
			em.close();
		if (emf != null)
			emf.close();
	}

	public static void closeEM() {
		if (em != null)
			em.close();
	}

	public static void openEM() {
		if (!em.isOpen())
			em = emf.createEntityManager();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data.IStorage#create(data.IEntity)
	 */
	@Override
	public boolean create(T t) {
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		// em.createStoredProcedureQuery()
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data.IStorage#readOne(data.QueryWithParam)
	 */
	@Override
	public T readOne(QueryWithParam<Q> query) {
		List<T> lst = read(query);

		return lst.size() == 1 ? lst.get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data.IStorage#read(data.QueryWithParam)
	 */
	@Override
	public List<T> read(QueryWithParam<Q> query) {
		Q queryEnum = query.getQuery();
		String quString = queryEnum.getQuery();

		TypedQuery<T> typedQuery = em.createQuery(quString, entityType);
		if (queryEnum.getParamName() != null)
			if (queryEnum.getParamName().indexOf(':') != -1) {
				String[] split = queryEnum.getParamName().split(":");
				Object[] values = (Object[]) query.getValue();
				if (split.length != values.length)
					throw new IllegalArgumentException("Incompatible value arrays");
				for (int i = 0; i < split.length; i++)
					typedQuery.setParameter(split[i], values[i]);
			} else
				typedQuery.setParameter(queryEnum.getParamName(), query.getValue());
		return typedQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data.IStorage#update(data.IEntity)
	 */
	@Override
	public boolean update(T t) {
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see data.IStorage#delete(data.IEntity)
	 */
	@Override
	public boolean delete(T t) {
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
		return true;
	}

	@Override
	public String storedFunction(QueryWithParam<FunctionJpaQueries> query, String a, String b) {
		String sum = (String) em.createNativeQuery(query.getQuery().getQuery()).setParameter(1, a).setParameter(2, b)
				.getSingleResult();
		return sum;

	}

	@Override
	public String storedFunction(QueryWithParam<FunctionJpaQueries> query, String a) {
		String salt = (String) em.createNativeQuery(query.getQuery().getQuery()).setParameter(1, a).getSingleResult();
		return salt;

	}

	@Override
	public void storedProcedure(QueryWithParam<ProcedureJpaQueries> query, Agent c, String... args) {
		String q = query.getQuery().getQuery();
		em.getTransaction().begin();

		StoredProcedureQuery storedQuery = em.createNamedStoredProcedureQuery(q);
		storedQuery.setParameter(args[0], c.getName());
		storedQuery.setParameter(args[1], args[2]);
		storedQuery.setParameter(args[3], args[4]);
		storedQuery.executeUpdate();

		em.getTransaction().commit();
	}

	@Override
	public void storedProcedure(QueryWithParam<ProcedureJpaQueries> query, String... args) {
		String q = query.getQuery().getQuery();
		em.getTransaction().begin();

		StoredProcedureQuery storedQuery = em.createNamedStoredProcedureQuery(q);
		storedQuery.setParameter(args[0], Integer.parseInt(args[1]));
		storedQuery.setParameter(args[2], Integer.parseInt(args[3]));
		storedQuery.setParameter(args[4], Integer.parseInt(args[5]));

		// storedQuery.setParameter("uid", Integer.parseInt(args[1]));
		// storedQuery.setParameter("bid", Integer.parseInt(args[3]));
		// storedQuery.setParameter("type", Integer.parseInt(args[5]));
		storedQuery.executeUpdate();

		em.getTransaction().commit();
	}

}
