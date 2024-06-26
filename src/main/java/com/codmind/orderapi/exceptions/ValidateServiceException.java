package com.codmind.orderapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidateServiceException extends RuntimeException{

    public ValidateServiceException() {
        super();
    }

    public ValidateServiceException(String message) {
        super(message);
    }

    public ValidateServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateServiceException(Throwable cause) {
        super(cause);
    }

    protected ValidateServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
