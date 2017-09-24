package com.file.sharing.exceptions;

public class UserEmailAlreadyInUseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE_FORMAT = "Email %s already in use.";

	private final String email;

	public UserEmailAlreadyInUseException(String email) {
		super(String.format(DEFAULT_MESSAGE_FORMAT, email));
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

}
