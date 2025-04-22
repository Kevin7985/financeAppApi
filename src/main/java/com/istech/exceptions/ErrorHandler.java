package com.istech.exceptions;

import com.istech.accounts.exceptions.AccountNotFoundException;
import com.istech.exceptions.models.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({
            AccountNotFoundException.class
    })
    public ApiError entityNotFoundExceptionHandler(final Exception e) {
        return new ApiError(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
    }
}
