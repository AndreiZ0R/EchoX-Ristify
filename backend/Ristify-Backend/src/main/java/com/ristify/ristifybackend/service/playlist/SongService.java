package com.ristify.ristifybackend.service.playlist;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.song.SongDTO;
import com.ristify.ristifybackend.models.playlist.Song;
import com.ristify.ristifybackend.repository.playlist.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream().map(DTOMapper::mapSongToDTO).collect(Collectors.toList());
    }

    public Optional<SongDTO> findUserById(final Integer songId) {
        return songRepository.findById(songId).map(DTOMapper::mapSongToDTO);
    }

    //TODO: maybe do this do other services
    public List<SongDTO> getPaginatedSongs(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Song> songPage = songRepository.findAll(pageable);
        return songPage.stream().map(DTOMapper::mapSongToDTO).collect(Collectors.toList());
    }

    public List<SongDTO> findByName(final String name) {
        return songRepository.findByName(name).stream().map(DTOMapper::mapSongToDTO).collect(Collectors.toList());
    }

    public List<SongDTO> findByArtist(final String artist) {
        return songRepository.findByArtist(artist).stream().map(DTOMapper::mapSongToDTO).collect(Collectors.toList());
    }

    public List<SongDTO> findByAlbum(final String album) {
        return songRepository.findByAlbum(album).stream().map(DTOMapper::mapSongToDTO).collect(Collectors.toList());
    }

    public Optional<SongDTO> saveSong(final Song song) {
        return Optional.of(DTOMapper.mapSongToDTO(songRepository.save(song)));
    }

    public Optional<SongDTO> deleteSongById(final Integer id) {
        return songRepository.deleteSongById(id).map(DTOMapper::mapSongToDTO);
    }
}