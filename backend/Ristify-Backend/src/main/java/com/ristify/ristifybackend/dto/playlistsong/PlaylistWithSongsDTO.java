package com.ristify.ristifybackend.dto.playlistsong;

import com.ristify.ristifybackend.dto.song.SongDTO;

import java.io.Serializable;
import java.util.List;

public record PlaylistWithSongsDTO(Integer playlistId, String name, List<SongDTO> songs) implements Serializable {
}
