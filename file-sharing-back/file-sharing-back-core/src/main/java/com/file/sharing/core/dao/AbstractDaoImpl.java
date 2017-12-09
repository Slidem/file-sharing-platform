package com.file.sharing.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractDaoImpl<T> implements AbstractDao<T> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> type;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AbstractDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public Serializable saveAndGetId(T entity) {
		Session s = entityManager.unwrap(Session.class);
		return s.save(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public Optional<T> find(Object id) {
		T entity = entityManager.find(type, id);
		return Optional.ofNullable(entity);
	}

	@Override
	public List<T> getAll() {
		TypedQuery<T> q = entityManager.createNamedQuery("from " + type.getName(), type);
		return q.getResultList();
	}

	@Override
	public void flush() {
		entityManager.flush();
	}
	
	protected Optional<T> getUniqueResult(TypedQuery<T> query, String nonUniqueResultMessage) {
		try {
			return Optional.of(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		} catch (NonUniqueResultException e) {
			logger.warn(nonUniqueResultMessage);
			throw e;
		}
	}
}
