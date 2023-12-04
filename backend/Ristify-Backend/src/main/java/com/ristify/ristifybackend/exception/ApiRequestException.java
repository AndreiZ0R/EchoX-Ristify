package com.ristify.ristifybackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiRequestException extends RuntimeException {
    private HttpStatus status;

    public ApiRequestException(final String message) {
        super(message);
    }

    public ApiRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApiRequestException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }

    public ApiRequestException(final String message, final Throwable cause, final HttpStatus status) {
        super(message, cause);
        this.status = status;
    }
}
