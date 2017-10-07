package com.file.sharing.core.exception;

/**
 * @author Alexandru
 *
 */
public class UserNotFoundException extends FileSharingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754181567981086601L;

	private static final String MESSAGE_FORMAT_EMAIL = "Requested user not found for email: %s";

	private static final String MESSAGE_FORMAT_ID = "Requested user not found for id: %d";

	public UserNotFoundException(Integer userId) {
		super(String.format(MESSAGE_FORMAT_ID, userId));
	}

	public UserNotFoundException(String email) {
		super(String.format(MESSAGE_FORMAT_EMAIL, email));
	}

}
