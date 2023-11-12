package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.dto.UserDTO;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.service.UserService;
import com.ristify.ristifybackend.utils.AbstractMessageController;
import com.ristify.ristifybackend.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppUtils.usersControllerEndpoint)
@Slf4j
public class UserController extends AbstractMessageController {

    private UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public Response getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return !users.isEmpty() ? successResponse(users) : failureResponse("Failed to fetch users", HttpStatus.NOT_FOUND);
    }
}