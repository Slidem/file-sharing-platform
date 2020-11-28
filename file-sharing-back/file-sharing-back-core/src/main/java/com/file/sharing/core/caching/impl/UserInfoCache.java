package com.file.sharing.core.caching.impl;

import com.file.sharing.core.objects.UserInfo;
import com.file.sharing.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import static com.file.sharing.core.objects.Constants.USER_INFO_CACHE_NAME;

/**
 * created by: andrei
 * date: 04.05.2019
 **/
@Component
public class UserInfoCache {

    UserService userService;

    @Autowired
    public UserInfoCache(UserService userService) {
        this.userService = userService;
    }

    @Cacheable(USER_INFO_CACHE_NAME)
    public UserInfo get(String email) {
        return userService.getUserInfoByEmail(email);
    }

    @CacheEvict(value = USER_INFO_CACHE_NAME, allEntries = true)
    public void evict() {}
}
