package com.file.sharing.exceptions;

import javax.servlet.http.HttpServletResponse;

public class UserNotFoundException extends FileSharingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4754181567981086601L;

	private static final String DEFAULT_MESSAGE = "Requested user not found";

	public UserNotFoundException(String message, Throwable cause) {
		super(HttpServletResponse.SC_NOT_FOUND, message, cause);
	}

	public UserNotFoundException(String message) {
		super(HttpServletResponse.SC_NOT_FOUND, message);
	}

	public UserNotFoundException() {
		super(HttpServletResponse.SC_NOT_FOUND, DEFAULT_MESSAGE);
	}

}
