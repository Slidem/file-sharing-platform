package com.file.sharing.core.events.impl;

import com.file.sharing.core.events.ItemActionInfoEvent;
import com.file.sharing.jms.commons.object.ItemActionTransactionInfo;

/**
 * created by: andrei
 * date: 04.05.2019
 **/
public class ItemActionInfoEventImpl implements ItemActionInfoEvent{

    ItemActionTransactionInfo itemActionInfo;

    public ItemActionInfoEventImpl(ItemActionTransactionInfo itemActionInfo) {
        this.itemActionInfo = itemActionInfo;
    }

    @Override
    public ItemActionTransactionInfo itemActionInfo() {
        return itemActionInfo;
    }
}
