package com.ristify.ristifybackend.service.playlist;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.playlist.PlaylistDTO;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistSongDTO;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistWithSongsDTO;
import com.ristify.ristifybackend.dto.playlistsong.CreatePlaylistSongRequest;
import com.ristify.ristifybackend.models.playlist.Playlist;
import com.ristify.ristifybackend.models.playlist.PlaylistSong;
import com.ristify.ristifybackend.models.playlist.Song;
import com.ristify.ristifybackend.repository.playlist.PlaylistRepository;
import com.ristify.ristifybackend.repository.playlist.PlaylistSongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: tests
@Service
@RequiredArgsConstructor
public class PlaylistSongService {
    private final PlaylistSongRepository playlistSongRepository;
    private final PlaylistRepository playlistRepository;

    public List<PlaylistSongDTO> findAll() {
        return playlistSongRepository.findAll().stream().map(DTOMapper::mapPlaylistSongDTO).collect(Collectors.toList());
    }

    public Optional<PlaylistWithSongsDTO> findPlaylistWithSongs(final Integer playlistId) {
        Optional<Playlist> playlistIdOptional = playlistRepository.findById(playlistId);

        if (playlistIdOptional.isPresent()) {
            List<Song> songs = playlistSongRepository.findAllSongsFromPlaylist(playlistIdOptional.get().getPlaylistId()).stream()
                    .map(PlaylistSong::getSong).toList();

            return playlistRepository.findById(playlistId)
                    .map(playlist -> DTOMapper.mapPlaylistSongDTO(playlist, songs));
        }

        return Optional.empty();
    }

    public Optional<PlaylistSongDTO> storePlaylistSong(final CreatePlaylistSongRequest createPlaylistSongRequest) {
        return playlistSongRepository.storePlaylistSong(createPlaylistSongRequest.playlistId(), createPlaylistSongRequest.songId())
                .map(DTOMapper::mapPlaylistSongDTO);
    }

    public Optional<PlaylistSongDTO> deleteSongFromPlaylist(final Integer playlistId, final Integer songId) {
        return playlistSongRepository.deleteSongFromPlaylist(playlistId, songId)
                .map(DTOMapper::mapPlaylistSongDTO);
    }

    public List<PlaylistDTO> findAllPlaylistsContainingSong(final Integer songId) {
        List<Integer> playlistIds = playlistSongRepository.findAllPlaylistsContainingSong(songId).stream()
                .map(this::mapPlaylistSongToPlaylistId)
                .collect(Collectors.toList());

        return playlistRepository.findMultipleById(playlistIds).stream().map(DTOMapper::mapPlaylistToDTO).collect(Collectors.toList());
    }


    private Integer mapPlaylistSongToPlaylistId(final PlaylistSong playlistSong) {
        return playlistSong.getPlaylist().getPlaylistId();
    }
}
