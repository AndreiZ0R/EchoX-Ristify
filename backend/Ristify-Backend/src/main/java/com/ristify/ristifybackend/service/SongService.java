package com.ristify.ristifybackend.service;

import com.ristify.ristifybackend.dto.DTOMapper;
import com.ristify.ristifybackend.dto.SongDTO;
import com.ristify.ristifybackend.models.Song;
import com.ristify.ristifybackend.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {
    private final SongRepository songRepository;

    @Autowired
    public SongService(final SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream().map(DTOMapper::mapSongToDTO).collect(Collectors.toList());
    }

    public Optional<SongDTO> findUserById(final Integer songId) {
        return songRepository.findById(songId).map(DTOMapper::mapSongToDTO);
    }

    public List<SongDTO> getPaginatedSongs(final int page, final int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Song> songPage = songRepository.findAll(pageable);
        return songPage.stream().map(DTOMapper::mapSongToDTO).collect(Collectors.toList());
    }

    public Optional<SongDTO> findByName(final String name) {
        return songRepository.findByName(name).map(DTOMapper::mapSongToDTO);
    }

    public Optional<SongDTO> findByArtist(final String artist) {
        return songRepository.findByArtist(artist).map(DTOMapper::mapSongToDTO);
    }

    public Optional<SongDTO> findByAlbum(final String album) {
        return songRepository.findByAlbum(album).map(DTOMapper::mapSongToDTO);
    }

    public Optional<SongDTO> saveSong(final Song song) {
        return Optional.of(DTOMapper.mapSongToDTO(songRepository.save(song)));
    }

    public Optional<SongDTO> deleteSongById(final Integer id) {
        songRepository.deleteById(id);
        return songRepository.findById(id).map(DTOMapper::mapSongToDTO);
    }
}