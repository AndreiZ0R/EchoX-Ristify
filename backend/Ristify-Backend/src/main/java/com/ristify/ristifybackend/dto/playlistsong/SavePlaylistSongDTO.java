package com.ristify.ristifybackend.dto.playlistsong;

import java.io.Serializable;

public record SavePlaylistSongDTO(Integer playlistId, Integer songId) implements Serializable {
}
