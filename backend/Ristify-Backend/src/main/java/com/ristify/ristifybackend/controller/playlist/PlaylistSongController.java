package com.ristify.ristifybackend.controller.playlist;

import com.ristify.ristifybackend.controller.ConcreteMessageController;
import com.ristify.ristifybackend.dto.playlist.PlaylistDTO;
import com.ristify.ristifybackend.dto.playlistsong.CreatePlaylistSongRequest;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistSongDTO;
import com.ristify.ristifybackend.models.playlist.Playlist;
import com.ristify.ristifybackend.models.playlist.PlaylistSong;
import com.ristify.ristifybackend.response.Response;
import com.ristify.ristifybackend.service.playlist.PlaylistSongService;
import com.ristify.ristifybackend.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//TODO: tests
@RestController
@RequestMapping(AppUtils.PLAYLIST_SONG_CONTROLLER_ENDPOINT)
@RequiredArgsConstructor
public class PlaylistSongController extends ConcreteMessageController {
    private final PlaylistSongService playlistSongService;

    @GetMapping
    public Response getAllPlaylistsSongs() {
        List<PlaylistSongDTO> playlistSongs = playlistSongService.findAll();
        return !playlistSongs.isEmpty() ?
               successResponse(playlistSongs) :
               failureResponse(AppUtils.constructFailedToFetch(PlaylistSong.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/songId={songId}")
    public Response getAllPlaylistsContainingSong(@PathVariable final Integer songId) {
        List<PlaylistDTO> playlists = playlistSongService.findAllPlaylistsContainingSong(songId);
        return !playlists.isEmpty() ?
               successResponse(playlists) :
               failureResponse(AppUtils.constructFailedToFetch(Playlist.class), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/forPlaylistId={playlistId}")
    public Response getPlaylistWithSongs(@PathVariable final Integer playlistId) {
        return playlistSongService.findPlaylistWithSongs(playlistId)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedToFetch(PlaylistSong.class), HttpStatus.BAD_REQUEST));
    }

    @PostMapping(consumes = "application/json")
    public Response addSongToPlaylist(@RequestBody final CreatePlaylistSongRequest createPlaylistSongRequest) {
        return playlistSongService.storePlaylistSong(createPlaylistSongRequest)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedSaveMessage(PlaylistSong.class), HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("playlistId={playlistId}&songId={songId}")
    public Response deleteSongFromPlaylist(@PathVariable final Integer playlistId, @PathVariable final Integer songId) {
        return playlistSongService.deleteSongFromPlaylist(playlistId, songId)
                .map(this::successResponse)
                .orElse(failureResponse(AppUtils.constructFailedDeleteMessage(PlaylistSong.class, Pair.of(playlistId, songId)), HttpStatus.NOT_FOUND));
    }
}
