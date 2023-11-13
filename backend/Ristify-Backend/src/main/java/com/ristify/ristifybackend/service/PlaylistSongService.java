package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.repository.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO: tests
@Service
public class PlaylistSongService {

    private final PlaylistSongRepository playlistSongRepository;

    @Autowired
    public PlaylistSongService(final PlaylistSongRepository playlistSongRepository) {
        this.playlistSongRepository = playlistSongRepository;
    }

    //TODO: implement this
}
