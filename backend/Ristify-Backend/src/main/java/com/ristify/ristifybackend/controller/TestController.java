package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.utils.AbstractMessageController;
import com.ristify.ristifybackend.utils.AppUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppUtils.testControllerEndpoint)
public class TestController extends AbstractMessageController {

    @GetMapping("/err")
    public Response getErrorResponse() {
        return failureResponse("Something happened");
    }

    @GetMapping("/success")
    public Response getSuccessResponse() {
        return successResponse("ok");
    }
}