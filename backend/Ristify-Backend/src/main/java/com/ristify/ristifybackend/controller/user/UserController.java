package com.ristify.ristifybackend.controller.user;

import com.ristify.ristifybackend.controller.ConcreteMessageController;
import com.ristify.ristifybackend.dto.user.UserDTO;
import com.ristify.ristifybackend.models.user.User;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.service.user.UserService;
import com.ristify.ristifybackend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(AppUtils.USERS_CONTROLLER_ENDPOINT)
@RequiredArgsConstructor
//@CrossOrigin(origins = "*")
public class UserController extends ConcreteMessageController {
    private final UserService userService;

    @GetMapping()
    public Response getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return !users.isEmpty() ?
               successResponse(users) :
               failureResponse(AppUtils.constructFailedToFetch(User.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable final Integer id) {
        return userService.findById(id)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(User.class, "id", id), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/username={username}")
    public Response getByUsername(@PathVariable final String username) {
        return userService.findByUsername(username)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(User.class, "username", username), HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Response deleteUserById(@PathVariable final Integer id) {
        return userService.deleteUserById(id)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedDeleteMessage(User.class, id), HttpStatus.NOT_FOUND));
    }

}