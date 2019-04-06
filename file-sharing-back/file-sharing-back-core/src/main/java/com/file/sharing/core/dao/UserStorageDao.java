package com.file.sharing.core.dao;

import com.file.sharing.core.entity.UserStorage;

import java.util.Optional;

/**
 * created by: andrei
 * date: 24.03.2019
 **/
public interface UserStorageDao extends AbstractDao<UserStorage> {

    public Optional<UserStorage> findByUserId(Integer userId);

}
