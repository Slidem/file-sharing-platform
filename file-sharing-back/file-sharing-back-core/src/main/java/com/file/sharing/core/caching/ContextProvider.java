package com.file.sharing.core.caching;

import com.file.sharing.core.objects.StorageInfo;
import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.StorageService;
import com.file.sharing.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * created by: andrei
 * date: 06.04.2019
 **/
@Component
public class ContextProvider {

    private UserService userService;
    private StorageService storageService;

    @Autowired
    public ContextProvider(UserService userService, StorageService storageService) {
        this.userService = userService;
        this.storageService = storageService;
    }

    @Cacheable("userInfo")
    public UserInfo getUserInfo(String email) {
        return userService.getUserInfoByEmail(email);
    }

    @Cacheable("storageInfo")
    public StorageInfo getStorageInfo(Integer userId) {
        return storageService.getUserStorageInfo(userId);
    }

}