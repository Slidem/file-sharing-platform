package com.file.sharing.core.caching;

import com.file.sharing.core.objects.StorageInfo;
import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.StorageService;
import com.file.sharing.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import static com.file.sharing.core.objects.Constants.STORAGE_INFO_CACHE_NAME;
import static com.file.sharing.core.objects.Constants.USER_INFO_CACHE_NAME;

/**
 * created by: andrei
 * date: 06.04.2019
 **/
@Component
public class CachedContextProvider {

    private UserService userService;
    private StorageService storageService;

    @Autowired
    public CachedContextProvider(UserService userService, StorageService storageService) {
        this.userService = userService;
        this.storageService = storageService;
    }

    @Cacheable(USER_INFO_CACHE_NAME)
    public UserInfo getUserInfo(String email) {
        return userService.getUserInfoByEmail(email);
    }

    @Cacheable(STORAGE_INFO_CACHE_NAME)
    public StorageInfo getStorageInfo(Integer userId) {
        return storageService.getUserStorageInfoFromDb(userId);
    }

}