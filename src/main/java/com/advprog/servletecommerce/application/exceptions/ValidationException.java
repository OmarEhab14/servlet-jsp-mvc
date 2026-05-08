package com.advprog.servletecommerce.application.exceptions;

import com.advprog.servletecommerce.application.enums.HttpStatus;

import java.util.Map;

public class ValidationException extends AppException {
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    public ValidationException(Map<String, String> errors) {
        super(STATUS.getCode(), "Validation error", "Request validation failed", errors);
    }
}
