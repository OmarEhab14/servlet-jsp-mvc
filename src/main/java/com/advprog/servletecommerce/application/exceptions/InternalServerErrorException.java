package com.advprog.servletecommerce.application.exceptions;

import com.advprog.servletecommerce.application.enums.HttpStatus;

import java.util.Collections;

public class InternalServerErrorException extends AppException {
    private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    public InternalServerErrorException() {
        super(STATUS.getCode(), "Internal Server Error", "Something went wrong", Collections.emptyMap());
    }
    public InternalServerErrorException(String message) {
        super(STATUS.getCode(), "Internal Server Error", message, Collections.emptyMap());
    }
}
