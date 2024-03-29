package com.ristify.ristifybackend.dto.song;

import java.io.Serializable;

public record SongDTO(Integer songId, String songName, String artistName, String albumName, String url)
        implements Serializable {
}