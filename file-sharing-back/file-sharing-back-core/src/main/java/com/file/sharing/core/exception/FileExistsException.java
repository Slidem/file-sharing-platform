package com.file.sharing.core.exception;

/**
 * @author Andrei Aioanei
 * @created 25.04.2019
 */
public class FileExistsException extends FileSharingException {

    /**
     *
     */
    private static final String MESSAGE_FORMAT_USER_ID_FILE_NAME = "File already exists for id: %d, name: %s";

    public FileExistsException(Integer userId, String fileName) {
        super(String.format(MESSAGE_FORMAT_USER_ID_FILE_NAME, userId, fileName));
    }
}
