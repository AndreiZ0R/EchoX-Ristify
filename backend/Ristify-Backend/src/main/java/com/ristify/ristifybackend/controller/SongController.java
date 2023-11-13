package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.dto.song.SongDTO;
import com.ristify.ristifybackend.models.Song;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.service.SongService;
import com.ristify.ristifybackend.utils.AbstractMessageController;
import com.ristify.ristifybackend.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
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

@RestController
@RequestMapping(AppUtils.songControllerEndpoint)
@Slf4j
public class SongController extends AbstractMessageController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public Response getAllSongs() {
        List<SongDTO> songs = songService.getAllSongs();
        return !songs.isEmpty() ?
                successResponse(songs) :
                failureResponse(AppUtils.constructFailedToFetch(Song.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page={page}&size={size}")
    public Response getPaginatedSongs(@PathVariable final int page, @PathVariable final int size) {
        List<SongDTO> paginatedSongs = songService.getPaginatedSongs(page, size);
        return !paginatedSongs.isEmpty() ?
                successResponse(paginatedSongs) :
                failureResponse(AppUtils.constructFailedToFetch(Song.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{songId}")
    public Response getSongById(@PathVariable final Integer songId) {
        return songService.findUserById(songId)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(Song.class, "id", songId), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name={name}")
    public Response getSongByName(@PathVariable final String name) {
        return songService.findByName(name)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(Song.class, "name", name), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/artist={artist}")
    public Response getSongByArtist(@PathVariable final String artist) {
        return songService.findByArtist(artist)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(Song.class, "artist", artist), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/album={album}")
    public Response getSongByAlbum(@PathVariable final String album) {
        return songService.findByAlbum(album)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(Song.class, "album", album), HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    public Response storeUser(@RequestBody final Song song) {
        return songService.saveSong(song)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedSaveMessage(Song.class), HttpStatus.BAD_REQUEST));
    }

    // TODO handle delete response
    @DeleteMapping("/{id}")
    public Response deleteSongById(@PathVariable final Integer id) {
        return songService.deleteSongById(id)
                .map(song -> failureResponse(AppUtils.constructFailedDeleteMessage(Song.class, song), HttpStatus.NOT_FOUND))
                .orElse(successResponse(AppUtils.constructSuccessDeleteMessage(Song.class, id)));
    }
}