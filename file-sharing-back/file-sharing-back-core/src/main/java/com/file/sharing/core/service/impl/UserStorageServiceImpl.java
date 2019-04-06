package com.file.sharing.core.service.impl;

import com.file.sharing.core.dao.UserStorageDao;
import com.file.sharing.core.dao.UsersDao;
import com.file.sharing.core.entity.User;
import com.file.sharing.core.entity.UserStorage;
import com.file.sharing.core.exception.UserNotFoundException;
import com.file.sharing.core.factory.UserStorageFactory;
import com.file.sharing.core.service.UserStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * created by: andrei
 * date: 24.03.2019
 **/
@Service
@Transactional
public class UserStorageServiceImpl implements UserStorageService {

    UsersDao usersDao;

    UserStorageDao userStorageDao;

    UserStorageFactory userStorageFactory;

    @Autowired
    public UserStorageServiceImpl(UsersDao usersDao, UserStorageDao userStorageDao, UserStorageFactory userStorageFactory) {
        this.usersDao = usersDao;
        this.userStorageDao = userStorageDao;
        this.userStorageFactory = userStorageFactory;
    }

    @Override
    public UserStorage createUserStorageByUserId(Integer userId) {
        Optional<User> user = usersDao.find(userId);
        if(!user.isPresent())
            throw new UserNotFoundException(userId);
        return userStorageDao.saveAndGet(userStorageFactory.fromUser(user.get()));
    }
}
