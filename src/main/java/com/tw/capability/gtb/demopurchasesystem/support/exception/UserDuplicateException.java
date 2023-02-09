package com.tw.capability.gtb.demopurchasesystem.support.exception;

public class UserDuplicateException extends RuntimeException {
    public UserDuplicateException(String username) {
        super("The username: " + username + " has been registered");
    }
}
