package com.file.sharing.core.dao;

import java.util.List;
import java.util.Optional;

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

	Optional<T> find(Object id);

	List<T> getAll();

	void flush();

}
