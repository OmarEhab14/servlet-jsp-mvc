package com.advprog.servletecommerce.application.exceptions.dtos;

import lombok.Builder;

import java.time.Instant;
import java.util.Map;

@Builder
public record ExceptionResponse(
        int status,
        String title,
        String detail,
        Instant timeStamp,
        Map<String, String> errors
) {
}
