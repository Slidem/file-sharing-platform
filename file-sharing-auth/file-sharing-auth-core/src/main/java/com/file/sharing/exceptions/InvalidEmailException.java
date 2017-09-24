package com.file.sharing.exceptions;

public class InvalidEmailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEmailException() {
		super();
	}

	public InvalidEmailException(String message) {
		super(message);
	}

}
