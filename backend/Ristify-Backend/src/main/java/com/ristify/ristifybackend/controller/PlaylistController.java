package com.ristify.ristifybackend.controller;

import com.ristify.ristifybackend.dto.PlaylistDTO;
import com.ristify.ristifybackend.dto.SavePlaylistDTO;
import com.ristify.ristifybackend.models.Playlist;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.service.PlaylistService;
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
@RequestMapping(AppUtils.playlistControllerEndpoint)
@Slf4j
public class PlaylistController extends AbstractMessageController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public Response getAllPlaylists() {
        List<PlaylistDTO> playlists = playlistService.getAllPlaylists();
        return !playlists.isEmpty() ?
                successResponse(playlists) :
                failureResponse(AppUtils.constructFailedToFetch(Playlist.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page={page}&size={size}")
    public Response getPaginatedSongs(@PathVariable final int page, @PathVariable final int size) {
        List<PlaylistDTO> paginatedPlaylists = playlistService.getPaginatedPlaylist(page, size);
        return !paginatedPlaylists.isEmpty() ?
                successResponse(paginatedPlaylists) :
                failureResponse(AppUtils.constructFailedToFetch(Playlist.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{playlistId}")
    public Response getPlaylistById(@PathVariable final Integer playlistId) {
        return playlistService.findById(playlistId)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(Playlist.class, "id", playlistId), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name={name}")
    public Response getPlaylistByName(@PathVariable final String name) {
        return playlistService.findByName(name)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructNotFoundMessage(Playlist.class, "name", name), HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    public Response storePlaylist(@RequestBody final SavePlaylistDTO savePlaylistDTO) {
        return playlistService.savePlaylist(savePlaylistDTO)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedSaveMessage(Playlist.class), HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public Response deletePlaylistById(@PathVariable final Integer id) {
        return playlistService.deletePlaylistById(id)
                .map(playlist -> failureResponse(AppUtils.constructFailedDeleteMessage(Playlist.class, playlist), HttpStatus.NOT_FOUND))
                .orElse(successResponse(AppUtils.constructSuccessDeleteMessage(Playlist.class, id)));
    }
}
