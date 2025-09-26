package com.vipusa.management.exception;

public class FirebaseOperationException extends RuntimeException {
    public FirebaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}