package com.file.sharing.core.factory;

import com.file.sharing.core.entity.User;
import com.file.sharing.core.entity.UserStorage;

/**
 * created by: andrei
 * date: 24.03.2019
 **/
public interface UserStorageFactory {

    public UserStorage fromUser(User user);
}
