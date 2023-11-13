package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.playlist.PlaylistDTO;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistSongDTO;
import com.ristify.ristifybackend.dto.playlistsong.PlaylistWithSongsDTO;
import com.ristify.ristifybackend.dto.playlistsong.SavePlaylistSongDTO;
import com.ristify.ristifybackend.models.Playlist;
import com.ristify.ristifybackend.models.PlaylistSong;
import com.ristify.ristifybackend.models.Song;
import com.ristify.ristifybackend.repository.PlaylistRepository;
import com.ristify.ristifybackend.repository.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//TODO: tests
@Service
public class PlaylistSongService {

    private final PlaylistSongRepository playlistSongRepository;
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistSongService(
            final PlaylistSongRepository playlistSongRepository,
            final PlaylistRepository playlistRepository) {
        this.playlistSongRepository = playlistSongRepository;
        this.playlistRepository = playlistRepository;
    }

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

    public Optional<PlaylistSongDTO> storePlaylistSong(final SavePlaylistSongDTO savePlaylistSongDTO) {
        return playlistSongRepository.storePlaylistSong(savePlaylistSongDTO.playlistId(), savePlaylistSongDTO.songId())
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
