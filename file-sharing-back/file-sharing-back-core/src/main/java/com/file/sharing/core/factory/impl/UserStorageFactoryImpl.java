package com.file.sharing.core.factory.impl;

import com.file.sharing.core.entity.User;
import com.file.sharing.core.entity.UserStorage;
import com.file.sharing.core.factory.UserStorageFactory;
import org.springframework.stereotype.Component;

/**
 * created by: andrei
 * date: 24.03.2019
 **/
@Component
public class UserStorageFactoryImpl implements UserStorageFactory {

    @Override
    public UserStorage fromUser(User user) {
        UserStorage userStorage = new UserStorage();
        userStorage.setUser(user);
        userStorage.setFiles(0);
        userStorage.setFolders(0);
        userStorage.setSizeUsed(0L);
        return userStorage;
    }
}
