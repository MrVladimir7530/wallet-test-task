package com.example.wallettesttask.exception;

public class WalletNotTrue extends RuntimeException{
    public WalletNotTrue() {
    }

    public WalletNotTrue(String message) {
        super(message);
    }

    public WalletNotTrue(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletNotTrue(Throwable cause) {
        super(cause);
    }

    public WalletNotTrue(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
