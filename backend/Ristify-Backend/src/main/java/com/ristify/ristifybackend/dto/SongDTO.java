package com.ristify.ristifybackend.dto;

import java.io.Serializable;

public record SongDTO(Integer songId, String songName, String artistName, String albumName)
        implements Serializable {
}