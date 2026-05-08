package com.advprog.servletecommerce.application.exceptions;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class AppException extends RuntimeException {
    private final int status;
    private final String message;
    private final String detail;
    private final Map<String, String> errors;
    public AppException(int status, String message, String detail, Map<String, String> errors) {
        super(message);
        this.message = message;
        this.status = status;
        this.detail = detail;
        this.errors = errors.isEmpty() ? Collections.emptyMap() : errors;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
