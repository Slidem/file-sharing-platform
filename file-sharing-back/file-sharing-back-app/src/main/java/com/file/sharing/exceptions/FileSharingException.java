package com.file.sharing.exceptions;

public class FileSharingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4754602467936167679L;

	protected int errorCode;

	private static final String DEFAULT_MESSAGE = "File sharing rest API has encountered an error";

	/**
	 * @param errorCode
	 * @param message
	 */
	public FileSharingException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public FileSharingException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public FileSharingException(int errorCode) {
		super(DEFAULT_MESSAGE);
	}

	public int getErrorCode() {
		return errorCode;
	}

}
