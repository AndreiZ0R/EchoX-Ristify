package com.ristify.ristifybackend.controller.playlist;

import com.ristify.ristifybackend.controller.ConcreteMessageController;
import com.ristify.ristifybackend.dto.playlist.CreatePlaylistRequest;
import com.ristify.ristifybackend.dto.playlist.PlaylistDTO;
import com.ristify.ristifybackend.models.playlist.Playlist;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.service.playlist.PlaylistService;
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

@RestController
@RequestMapping(AppUtils.PLAYLIST_CONTROLLER_ENDPOINT)
@RequiredArgsConstructor
public class PlaylistController extends ConcreteMessageController {
    private final PlaylistService playlistService;

    @GetMapping
    public Response getAllPlaylists() {
        List<PlaylistDTO> playlists = playlistService.findAllPlaylists();
        return !playlists.isEmpty() ?
               successResponse(playlists) :
               failureResponse(AppUtils.constructFailedToFetch(Playlist.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/page={page}&size={size}")
    public Response getPaginatedPlaylists(@PathVariable final int page, @PathVariable final int size) {
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
    public Response storePlaylist(@RequestBody final CreatePlaylistRequest createPlaylistRequest) {
        return playlistService.savePlaylist(createPlaylistRequest)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedSaveMessage(Playlist.class), HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public Response deletePlaylistById(@PathVariable final Integer id) {
        return playlistService.deletePlaylistById(id)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedDeleteMessage(Playlist.class, id), HttpStatus.NOT_FOUND));
    }
}
