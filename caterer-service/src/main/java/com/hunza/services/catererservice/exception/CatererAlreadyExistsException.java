package com.hunza.services.catererservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CatererAlreadyExistsException extends RuntimeException {

    public CatererAlreadyExistsException() {
        super();
    }

    public CatererAlreadyExistsException(String message) {
        super(message);
    }

    public CatererAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}


