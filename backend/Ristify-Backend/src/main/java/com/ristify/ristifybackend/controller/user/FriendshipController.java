package com.ristify.ristifybackend.controller.user;

import com.ristify.ristifybackend.controller.ConcreteMessageController;
import com.ristify.ristifybackend.dto.friendship.CreateFriendshipRequest;
import com.ristify.ristifybackend.dto.friendship.FriendshipDTO;
import com.ristify.ristifybackend.models.user.Friendship;
import com.ristify.ristifybackend.models.user.User;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.service.user.FriendshipService;
import com.ristify.ristifybackend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO: tests - Andrei
@RestController
@RequestMapping(AppUtils.FRIENDSHIPS_CONTROLLER_ENDPOINT)
@RequiredArgsConstructor
public class FriendshipController extends ConcreteMessageController {
    private final FriendshipService friendshipService;

    @GetMapping
    public Response getAllFriendships() {
        List<FriendshipDTO> friendships = friendshipService.getAllFriendships();
        return !friendships.isEmpty() ?
               successResponse(friendships) :
               failureResponse(AppUtils.constructFailedToFetch(Friendship.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/firstId={id1}&secondId={id2}")
    public Response findFriendship(@PathVariable final Integer id1, @PathVariable final Integer id2) {
        return friendshipService.findFriendship(id1, id2)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(Friendship.class, "ids", List.of(id1, id2))));
    }

    @PostMapping(consumes = "application/json")
    public Response storeFriendship(@RequestBody final CreateFriendshipRequest createFriendshipRequest) {
        return friendshipService.saveFriendship(createFriendshipRequest)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedSaveMessage(User.class), HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/firstId={id1}&secondId={id2}")
    public Response deleteFriendship(@PathVariable final Integer id1, @PathVariable final Integer id2) {
        return friendshipService.deleteFriendship(id1, id2)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedDeleteMessage(Friendship.class, List.of(id1, id2)), HttpStatus.NOT_FOUND));
    }
}