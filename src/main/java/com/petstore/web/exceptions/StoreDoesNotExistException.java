package com.petstore.web.exceptions;

public class StoreDoesNotExistException extends Exception{
    public StoreDoesNotExistException() {
        super();
    }

    public StoreDoesNotExistException(String message) {
        super(message);
    }

    public StoreDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreDoesNotExistException(Throwable cause) {
        super(cause);
    }

    protected StoreDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
