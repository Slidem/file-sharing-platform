package com.file.sharing.core.actions;

import java.time.Instant;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public interface ItemAction {

	/**
	 * @return The id of the user performing the item action.
	 */
	Integer getUserId();

	/**
	 * @return The name of the item the action is performed on.
	 */
	String getItemName();

	/**
	 * @return The path of the item the action is performed on.
	 */
	String getPath();

	/**
	 * @return
	 */
	Instant getActionTime();

}
