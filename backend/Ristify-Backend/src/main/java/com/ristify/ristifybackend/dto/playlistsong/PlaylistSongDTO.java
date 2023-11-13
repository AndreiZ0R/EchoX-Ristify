package com.ristify.ristifybackend.dto.playlistsong;

import com.ristify.ristifybackend.dto.song.SongDTO;

import java.io.Serializable;

public record PlaylistSongDTO(Integer playlistId, SongDTO song) implements Serializable {
}
