package com.istech.accounts.exceptions;

public class AccountValidationException extends RuntimeException {
    public AccountValidationException(String message) {
        super(message);
    }
}
