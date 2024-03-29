package com.ristify.ristifybackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ristify.ristifybackend.response.BasicResponse;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.utils.AppUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ConcreteMessageController {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public Response successResponse(final Object body) {
        return response(body, HttpStatus.OK, AppUtils.SUCCESS);
    }

    public Response failureResponse(final String errorMessage) {
        return failureResponse(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Response failureResponse(final String errorMessage, final HttpStatus errorStatus) {
        return new Response.Builder()
                .withMessage(errorMessage)
                .withStatus(errorStatus)
                .build();
    }

    public Response response(final Object body, final HttpStatus status, final String message) {
        return new Response.Builder()
                .withMessage(message)
                .withStatus(status)
                .withBody(body)
                .build();
    }

    public Response response(final Object body, final String headerName, final String headerValue, final HttpStatus status, final String message) {
        return new Response.Builder()
                .withMessage(message)
                .withStatus(status)
                .withBody(body)
                .withHeader(headerName, headerValue)
                .build();
    }

    public Response response(final Object body, final HttpHeaders headers, final HttpStatus status, final String message) {
        return new Response.Builder()
                .withMessage(message)
                .withStatus(status)
                .withBody(body)
                .withHeaders(headers)
                .build();
    }

    public Response response(final Object body, final HttpStatus status, final String message, final Map<String, Object> extraFields) {
        return new Response.Builder()
                .withMessage(message)
                .withStatus(status)
                .withBody(body)
                .withMultipleFields(extraFields)
                .build();
    }

    @SneakyThrows
    public static void setServletResponse(final HttpServletResponse response, final String message, final int status) {
        response.setContentType(AppUtils.APPLICATION_JSON);
        response.setStatus(status);
        response.getWriter().println(objectMapper.writeValueAsString(basicResponse(message, HttpStatus.valueOf(status))));
    }

    public static BasicResponse basicResponse(final String message, final HttpStatus status) {
        return BasicResponse.builder()
                .message(message)
                .status(status)
                .build();
    }
}
