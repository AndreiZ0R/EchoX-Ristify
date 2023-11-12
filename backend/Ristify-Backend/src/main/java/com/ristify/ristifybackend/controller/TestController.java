package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.repository.FriendshipRepository;
import com.ristify.ristifybackend.repository.PlaylistSongRepository;
import com.ristify.ristifybackend.repository.UserRepository;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.utils.AbstractMessageController;
import com.ristify.ristifybackend.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppUtils.testControllerEndpoint)
public class TestController extends AbstractMessageController {
    private UserRepository userRepository;
    private FriendshipRepository friendshipRepository;
    private PlaylistSongRepository playlistSongRepository;

    @Autowired
    public TestController(
            final UserRepository userRepository,
            final FriendshipRepository friendshipRepository,
            final PlaylistSongRepository playlistSongRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
        this.playlistSongRepository = playlistSongRepository;
    }

    @GetMapping("/err")
    public Response getErrorResponse() {
        return failureResponse("Something happened");
    }

    @GetMapping("/success")
    public Response getSuccessResponse() {
        return successResponse("ok");
    }

    @GetMapping("/users")
    public Response getAllUsers() {
        return successResponse(userRepository.findAll());
    }

    @GetMapping("/users/containing={pattern}")
    public Response getAllUsersContaingPattern(@PathVariable final String pattern) {
        return successResponse(userRepository.findByUsernameContaining(pattern));
    }

    @GetMapping("/users/{id}/friends")
    public Response getAllUsersFriends(@PathVariable final Integer id) {
        return successResponse(friendshipRepository.getAllFriends(id));
    }

    @GetMapping("/playlists/{id}/songs")
    public Response getAllPlaylistSongs(@PathVariable final Integer id) {
        return successResponse(playlistSongRepository.getAllSongsFromPlaylist(id));
    }
}