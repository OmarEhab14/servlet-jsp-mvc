package com.advprog.servletecommerce.application.mappers;

import com.advprog.servletecommerce.application.exceptions.AppException;
import com.advprog.servletecommerce.application.exceptions.dtos.ExceptionResponse;

import java.time.Instant;

public class ExceptionMapper {
    public static ExceptionResponse toExceptionResponse(AppException e) {
        return ExceptionResponse.builder()
                .status(e.getStatus())
                .title(e.getMessage())
                .detail(e.getDetail())
                .errors(e.getErrors())
                .timeStamp(Instant.now())
                .build();
    }
}
