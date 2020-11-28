package com.file.sharing.core.handler.event;

import com.file.sharing.core.caching.impl.StorageInfoCache;
import com.file.sharing.core.objects.file.ItemActionType;
import com.file.sharing.jms.commons.object.ItemActionTransactionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * created by: andrei
 * date: 04.05.2019
 **/
@Component
public class ItemActionTransactionInfoEventHandler {

    private StorageInfoCache storageInfoCache;

    @Autowired
    public ItemActionTransactionInfoEventHandler(StorageInfoCache storageInfoCache) {
        this.storageInfoCache = storageInfoCache;
    }

    @EventListener
    public void handleCacheEvent(ItemActionTransactionInfo itemActionInfoEvent) {
        if(evictsCache(itemActionInfoEvent)) {
            storageInfoCache.evict();
        }

    }

    //----------------------------------------------------------------------------------------

    private boolean evictsCache(ItemActionTransactionInfo itemActionTransactionInfo) {
        EnumSet<ItemActionType> cacheEvictingActionTypes = EnumSet.of(ItemActionType.DELETE_FILE, ItemActionType.UPLOAD_FILE, ItemActionType.DELETE_DIRECTORY);
        return cacheEvictingActionTypes.contains(ItemActionType.valueOf(itemActionTransactionInfo.getItemAction()));
    }
}
