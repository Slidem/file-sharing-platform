package com.file.sharing.core.events;

import java.time.Instant;
import java.util.Optional;

import com.file.sharing.core.objects.file.FileTransactionStatus;
import com.file.sharing.core.objects.file.FileTransactionType;

/**
 * 
 * Item transaction event: create, move, delete, rename
 * 
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 * 
 */
public interface ItemTransactionEvent {

	/**
	 * @return
	 */
	Instant eventDuration();

	/**
	 * @return The parent of the file. If no parent is return, the parent of the
	 *         file is the root folder.
	 */
	Optional<Integer> parentId();

	/**
	 * @return
	 */
	String itemName();

	/**
	 * @return unique id of the transaction
	 */
	String transactionId();

	/**
	 * @return Id of the user that initiated the transaction.
	 */
	Integer userId();

	/**
	 * @return type of the transaction : create, move, delete, rename, etc...
	 */
	FileTransactionType type();

	/**
	 * @return the status of the transaction: SUCCESS, FAILED
	 */
	FileTransactionStatus status();

}
