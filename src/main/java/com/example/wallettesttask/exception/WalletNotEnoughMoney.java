package com.example.wallettesttask.exception;

public class WalletNotEnoughMoney extends RuntimeException{
    public WalletNotEnoughMoney() {
    }

    public WalletNotEnoughMoney(String message) {
        super(message);
    }

    public WalletNotEnoughMoney(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletNotEnoughMoney(Throwable cause) {
        super(cause);
    }

    public WalletNotEnoughMoney(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
