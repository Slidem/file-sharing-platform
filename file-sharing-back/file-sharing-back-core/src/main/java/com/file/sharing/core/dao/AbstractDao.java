package com.file.sharing.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Alexandru Mihai
 * @created Dec 9, 2017
 */
public interface AbstractDao<T> {

    void save(T entity);

    Serializable saveAndGetId(T entity);

    void delete(T entity);

    Optional<T> find(Object id);

    List<T> getAll();

    void flush();

}
