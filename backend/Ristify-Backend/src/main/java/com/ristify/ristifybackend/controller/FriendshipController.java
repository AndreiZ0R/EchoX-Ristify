package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.dto.friendship.FriendshipDTO;
import com.ristify.ristifybackend.dto.friendship.SaveFriendshipDTO;
import com.ristify.ristifybackend.models.Friendship;
import com.ristify.ristifybackend.models.User;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.service.FriendshipService;
import com.ristify.ristifybackend.utils.AbstractMessageController;
import com.ristify.ristifybackend.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(AppUtils.friendshipsControllerEndpoint)
public class FriendshipController extends AbstractMessageController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(final FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

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
    public Response storeFriendship(@RequestBody final SaveFriendshipDTO saveFriendshipDTO) {
        return friendshipService.saveFriendship(saveFriendshipDTO)
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