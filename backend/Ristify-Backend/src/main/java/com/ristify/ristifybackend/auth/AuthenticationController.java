package com.ristify.ristifybackend.auth;

import com.ristify.ristifybackend.auth.message.AuthenticationRequest;
import com.ristify.ristifybackend.auth.message.RegisterRequest;
import com.ristify.ristifybackend.controller.ConcreteMessageController;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@RequestMapping(AppUtils.AUTHENTICATION_CONTROLLER_ENDPOINT)
@RequiredArgsConstructor
public class AuthenticationController extends ConcreteMessageController {

    private final AuthenticationService service;

    @PostMapping(value = "/register", consumes = AppUtils.APPLICATION_JSON)
    public Response register(@RequestBody final RegisterRequest registerRequest) {
        return successResponse(service.register(registerRequest));
    }

    @PostMapping("/login")
    public Response authenticate(@RequestBody final AuthenticationRequest authenticationRequest) {
        return service.login(authenticationRequest)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.UNAUTHORIZED, HttpStatus.UNAUTHORIZED));
    }
}
