package com.file.sharing.core.exception;

public class UserStorageNotFoundException extends FileSharingException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 310756909883402382L;
	
	private static final String MESSAGE_FORMAT_PATH = "User storage not found for path: %s";
	
	public UserStorageNotFoundException(String path) {
		super(String.format(MESSAGE_FORMAT_PATH, path));
	}
	
	public UserStorageNotFoundException(String path, Throwable cause) {
		super(String.format(MESSAGE_FORMAT_PATH, path),cause);
	}
}
