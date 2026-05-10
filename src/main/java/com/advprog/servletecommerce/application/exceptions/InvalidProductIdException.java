package com.advprog.servletecommerce.application.exceptions;

import com.advprog.servletecommerce.application.enums.HttpStatus;

public class InvalidProductIdException extends AppException{
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    public InvalidProductIdException() {
        super(STATUS.getCode(),"Bad Request","Product id should be a valid number");
    }
    public InvalidProductIdException(String value) {
        super(STATUS.getCode(),"Bad Request","Invalid product id: "+value);
    }

}
