package com.file.sharing.core.events;

import com.file.sharing.jms.commons.object.ItemActionTransactionInfo;

/**
 * created by: andrei
 * date: 04.05.2019
 **/
public interface ItemActionInfoEvent {

    ItemActionTransactionInfo itemActionInfo();
}
