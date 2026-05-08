package com.advprog.servletecommerce.application.exceptions;

import com.advprog.servletecommerce.application.enums.HttpStatus;

import java.util.Collections;

public class UnauthorizedException extends AppException {
    private static final HttpStatus STATUS = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException() {
        super(STATUS.getCode(), "Unauthorized", "You are not allowed to do this action", Collections.emptyMap());
    }

    public UnauthorizedException(String message) {
        super(STATUS.getCode(), "Unauthorized", message, Collections.emptyMap());
    }
}
