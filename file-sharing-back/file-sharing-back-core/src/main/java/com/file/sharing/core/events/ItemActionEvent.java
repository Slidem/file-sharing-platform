package com.file.sharing.core.events;

import org.springframework.core.ResolvableTypeProvider;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.objects.file.ItemActionStatus;

/**
 * 
 * Item transaction event: create, move, delete, rename
 * 
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 * 
 */
public interface ItemActionEvent<T extends ItemAction> extends ResolvableTypeProvider {

	/**
	 * @return duration of the event in nano seconds
	 */
	long eventDuration();

	/**
	 * @return
	 */
	T itemAction();
	/**
	 * @return the status of the transaction: SUCCESS, FAILED
	 */
	ItemActionStatus status();

}
