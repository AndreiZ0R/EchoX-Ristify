package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.service.PlaylistSongService;
import com.ristify.ristifybackend.utils.AbstractMessageController;
import com.ristify.ristifybackend.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: tests
@RestController
@RequestMapping(AppUtils.playlistSongControllerEndpoint)
public class PlaylistSongController extends AbstractMessageController {

    private final PlaylistSongService playlistSongService;

    @Autowired
    public PlaylistSongController(final PlaylistSongService playlistSongService) {
        this.playlistSongService = playlistSongService;
    }

    //TODO: implement
}
