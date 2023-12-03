package com.ristify.ristifybackend.exception;

import com.ristify.ristifybackend.controller.ConcreteMessageController;
import com.ristify.ristifybackend.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class ApiExceptionHandler extends ConcreteMessageController {

    @ExceptionHandler(value = ApiRequestException.class)
    public Response handleApiRequestException(final ApiRequestException exception) {
        return failureResponse(exception.getMessage(), exception.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public Response handleException(final Exception exception) {
        return failureResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
