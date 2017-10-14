package com.file.sharing.core.exception;

public class UserExistsException extends FileSharingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3723426236373806678L;

	private static final String MESSAGE_FORMAT_ID = "User already exists for id: %d";

	public UserExistsException(Integer user_id) {
		super(String.format(MESSAGE_FORMAT_ID, user_id));
	}
}
