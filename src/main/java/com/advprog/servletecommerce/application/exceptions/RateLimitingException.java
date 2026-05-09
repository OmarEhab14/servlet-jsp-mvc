package com.advprog.servletecommerce.application.exceptions;

import com.advprog.servletecommerce.application.enums.HttpStatus;

import java.util.Collections;

public class RateLimitingException extends AppException {

    private static final HttpStatus STATUS =
            HttpStatus.RATE_LIMITING;

    public RateLimitingException() {

        super(
                STATUS.getCode(),
                "Too Many Requests",
                "Rate limit exceeded",
                Collections.emptyMap()
        );
    }

    public RateLimitingException(String message) {

        super(
                STATUS.getCode(),
                "Too Many Requests",
                message,
                Collections.emptyMap()
        );
    }
}