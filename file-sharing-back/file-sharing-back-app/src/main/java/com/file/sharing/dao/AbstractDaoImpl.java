package com.file.sharing.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDaoImpl<T> implements AbstractDao<T> {

	@Autowired
	protected SessionFactory sessionFactory;

	private Class<T> type;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AbstractDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		sessionFactory.getCurrentSession().remove(entity);
	}

	@Override
	public T find(Object id) {
		return (T) sessionFactory.getCurrentSession().find(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		Query<T> q = sessionFactory.getCurrentSession().createQuery("from " + type.getName());
		return q.getResultList();
	}

}
