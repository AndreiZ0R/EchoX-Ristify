package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.playlist.PlaylistDTO;
import com.ristify.ristifybackend.dto.playlist.SavePlaylistDTO;
import com.ristify.ristifybackend.models.Playlist;
import com.ristify.ristifybackend.models.User;
import com.ristify.ristifybackend.repository.PlaylistRepository;
import com.ristify.ristifybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
    }

    public List<PlaylistDTO> findAllPlaylists() {
        return playlistRepository.findAll().stream().map(DTOMapper::mapPlaylistToDTO).collect(Collectors.toList());
    }

    public Optional<PlaylistDTO> findById(final Integer id) {
        return playlistRepository.findById(id).map(DTOMapper::mapPlaylistToDTO);
    }

    public List<PlaylistDTO> getPaginatedPlaylist(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Playlist> playlistPage = playlistRepository.findAll(pageable);
        return playlistPage.stream().map(DTOMapper::mapPlaylistToDTO).collect(Collectors.toList());
    }

    public Optional<PlaylistDTO> findByName(final String name) { return playlistRepository.findByName(name).map(DTOMapper::mapPlaylistToDTO); }

    public Optional<PlaylistDTO> savePlaylist(final SavePlaylistDTO savePlaylistDTO) {
        Optional<User> userOptional = userRepository.findById(savePlaylistDTO.userId());

        if (userOptional.isPresent()) {
            playlistRepository.storePlaylist(savePlaylistDTO.userId(), savePlaylistDTO.name());
            return playlistRepository.findByName(savePlaylistDTO.name()).map(DTOMapper::mapPlaylistToDTO);
        }

        return Optional.empty();
    }

    public Optional<PlaylistDTO> deletePlaylistById(final Integer id) {
        return playlistRepository.deletePlaylistById(id).map(DTOMapper::mapPlaylistToDTO);
    }
}