package com.example.notes_app.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BaseException {

    public InvalidTokenException(String message, HttpStatus httpStatusCode) {
        super(message, httpStatusCode);
    }

}
