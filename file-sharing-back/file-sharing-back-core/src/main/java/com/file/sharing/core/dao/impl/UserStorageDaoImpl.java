package com.file.sharing.core.dao.impl;

import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.UserStorageDao;
import com.file.sharing.core.entity.UserStorage;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Optional;

/**
 * created by: andrei
 * date: 24.03.2019
 **/
@Repository
public class UserStorageDaoImpl extends AbstractDaoImpl<UserStorage> implements UserStorageDao {

    @Override
    public Optional<UserStorage> findByUserId(Integer userId) {
        TypedQuery<UserStorage> query = entityManager.createQuery("from UserStorage u where u.userId=:userId", UserStorage.class);
        query.setParameter("userId", userId);
        return getUniqueResult(query, "More than one user storage found for userId: " + userId);
    }
}
