package com.example.notes_app.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException(String message, HttpStatus httpStatusCode) {
        super(message, httpStatusCode);
    }

}
