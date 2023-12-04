package com.ristify.ristifybackend.dto.playlistsong;

import java.io.Serializable;

public record CreatePlaylistSongRequest(Integer playlistId, Integer songId) implements Serializable {
}
