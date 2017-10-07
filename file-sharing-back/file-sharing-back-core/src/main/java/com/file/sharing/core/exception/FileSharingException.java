package com.file.sharing.core.exception;

public class FileSharingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4754602467936167679L;

	private static final String DEFAULT_MESSAGE = "File sharing rest API has encountered an error";

	/**
	 * @param errorCode
	 * @param message
	 */
	public FileSharingException(String message) {
		super(message);
	}

	public FileSharingException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileSharingException() {
		super(DEFAULT_MESSAGE);
	}

}
