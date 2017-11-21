package com.file.sharing.core.handler.action;

import com.file.sharing.core.actions.ItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public interface ItemActionHandler<T extends ItemAction> {

	/**
	 * @param action
	 */
	void handle(T action);

	/**
	 * @return
	 */
	Class<T> getActionClass();

}
