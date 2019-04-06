package com.file.sharing.core.business;

import com.file.sharing.core.entity.UserStorage;

import java.util.Optional;

/**
 * created by: andrei
 * date: 24.03.2019
 **/
public interface UserStorageBusiness {

    public Optional<UserStorage> getUserStorageByUserId(Integer userId);

    public void saveUserStorage(UserStorage userStorage);
}
