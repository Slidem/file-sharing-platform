package com.file.sharing.dao;

import java.util.List;

/**
 * 
 * @author Alexandru
 *
 * @param <T>
 */
public interface AbstractDao<T> {

	// TODO implement other functionalities that are general for most of the DAO
	// concrete implementations
	void save(T entity);

	void delete(T entity);

	T find(Object id);

	List<T> getAll();

}
