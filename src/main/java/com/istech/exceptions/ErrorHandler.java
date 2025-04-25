package com.istech.exceptions;

import com.istech.accounts.exceptions.AccountNotFoundException;
import com.istech.accounts.exceptions.AccountValidationException;
import com.istech.exceptions.models.ApiError;
import com.istech.operations.exceptions.OperationValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({
            AccountNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError entityNotFoundExceptionHandler(final Exception e) {
        return new ApiError(
                HttpStatus.NOT_FOUND.toString(),
                e.getMessage()
        );
    }

    @ExceptionHandler({
            OperationValidationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError entityValidationCustomExceptionHandler(final Exception e) {
        return new ApiError(
                HttpStatus.BAD_REQUEST.toString(),
                e.getMessage()
        );
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            AccountValidationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError entityValidationExceptionHandler(final MethodArgumentNotValidException e) {
        return new ApiError(
                HttpStatus.BAD_REQUEST.toString(),
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError jsonResponseNotReadableExceptionHandler(final Exception e) {
        return new ApiError(
                HttpStatus.BAD_REQUEST.toString(),
                e.getMessage()
        );
    }
}
