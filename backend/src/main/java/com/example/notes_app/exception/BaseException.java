package com.example.notes_app.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final HttpStatus httpStatusCode;
    private final String message;

    public BaseException(String message, HttpStatus httpStatusCode) {
        super(message);
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
