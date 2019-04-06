package com.file.sharing.core.business.impl;

import com.file.sharing.core.business.UserStorageBusiness;
import com.file.sharing.core.dao.UserStorageDao;
import com.file.sharing.core.entity.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * created by: andrei
 * date: 24.03.2019
 **/
@Component
public class UserStorageBusinessImpl implements UserStorageBusiness {

    @Autowired
    UserStorageDao userStorageDao;

    @Override
    public Optional<UserStorage> getUserStorageByUserId(Integer userId) {
        return userStorageDao.findByUserId(userId);
    }

    @Override
    public void saveUserStorage(UserStorage userStorage) {
        userStorageDao.save(userStorage);
    }
}
