package com.file.sharing.rest.exception;

import com.file.sharing.core.exception.FileSharingException;

/**
 * @author Alexandru
 *
 */
public class InvalidEmailException extends FileSharingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEmailException(String email) {
		super("Invalid email: " + email);
	}

}
