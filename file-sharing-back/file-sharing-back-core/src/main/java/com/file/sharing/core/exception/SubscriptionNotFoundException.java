package com.file.sharing.core.exception;

/**
 * created by: andrei
 * date: 06.04.2019
 **/
public class SubscriptionNotFoundException extends FileSharingException {

    private static final String MESSAGE_FORMAT_USER_ID = "No subscription data was found for user with id: %s";

    public SubscriptionNotFoundException(int userId) {
        super(String.format(MESSAGE_FORMAT_USER_ID, userId));
    }
}
