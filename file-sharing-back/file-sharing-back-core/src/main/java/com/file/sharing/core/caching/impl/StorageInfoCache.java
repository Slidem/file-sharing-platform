package com.file.sharing.core.caching.impl;

import com.file.sharing.core.objects.StorageInfo;
import com.file.sharing.core.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import static com.file.sharing.core.objects.Constants.STORAGE_INFO_CACHE_NAME;

/**
 * created by: andrei
 * date: 04.05.2019
 **/
@Component
public class StorageInfoCache {

    private final static Logger log = LoggerFactory.getLogger(StorageInfoCache.class);

    StorageService storageService;

    @Autowired
    public StorageInfoCache(StorageService storageService) {
        this.storageService = storageService;
    }

    @Cacheable(STORAGE_INFO_CACHE_NAME)
    public StorageInfo get(Integer userId) {
        return storageService.getUserStorageInfoFromDb(userId);
    }

    @CacheEvict(value = STORAGE_INFO_CACHE_NAME, allEntries = true)
    public void evict() {
        log.info("Storage info cache evicted!");
    }
}
