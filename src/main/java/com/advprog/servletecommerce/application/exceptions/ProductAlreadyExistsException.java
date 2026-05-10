package com.advprog.servletecommerce.application.exceptions;

import com.advprog.servletecommerce.application.enums.HttpStatus;

public class ProductAlreadyExistsException extends AppException{
    private static final HttpStatus STATUS = HttpStatus.CONFLICT;
    public ProductAlreadyExistsException() {
        super(STATUS.getCode(),"Conflict","Product already exists");
    }

    public ProductAlreadyExistsException(Long id) {
        super(STATUS.getCode(), "Conflict",  "Product with id " + id + " already exists");
    }

    public ProductAlreadyExistsException(String message) {
        super(STATUS.getCode(),"Conflict", message);
    }

}
