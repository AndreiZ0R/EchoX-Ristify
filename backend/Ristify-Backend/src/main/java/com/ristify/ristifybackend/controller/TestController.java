package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.utils.AppUtils;
import com.ristify.ristifybackend.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(AppUtils.testControllerEndpoint)
public class TestController extends AbstractMessageController {

    @GetMapping
    public Response getTestResponse() {
        User user = new User("Andrei", 21);
        return successResponse(user);
    }

    @GetMapping("/err")
    public Response getErrorResponse() {
        return failureResponse("Something happened");
    }

    @GetMapping("/br")
    public Response brbrbr() {
        return response("baiok", HttpStatus.NOT_FOUND, "responseDeita", Map.of("token", "jwt---431431"));
    }

    @GetMapping("/headers")
    public ResponseEntity<Object> withHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("sexcox123", "jwt-431431431431");

        return new ResponseEntity<>(new User("Amigo", 25), headers, HttpStatus.OK);
    }
}
