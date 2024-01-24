package com.example.wallettesttask.exception;

public class WalletDoesNotExist extends RuntimeException{

    public WalletDoesNotExist() {
    }

    public WalletDoesNotExist(String message) {
        super(message);
    }

    public WalletDoesNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletDoesNotExist(Throwable cause) {
        super(cause);
    }

    public WalletDoesNotExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
