package com.file.sharing.core.service;

import com.file.sharing.core.entity.UserStorage;

/**
 * created by: andrei
 * date: 24.03.2019
 **/
public interface UserStorageService {

    public UserStorage createUserStorageByUserId(Integer userId);
}
