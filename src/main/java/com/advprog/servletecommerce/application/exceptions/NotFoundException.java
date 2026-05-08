package com.advprog.servletecommerce.application.exceptions;

import com.advprog.servletecommerce.application.enums.HttpStatus;

import java.util.Collections;

public class NotFoundException extends AppException {
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
    public NotFoundException() {
        super(STATUS.getCode(), "Not Found", "Resource not found", Collections.emptyMap());
    }
    public NotFoundException(String message) {
        super(STATUS.getCode(), "Not Found", message, Collections.emptyMap());
    }
}
