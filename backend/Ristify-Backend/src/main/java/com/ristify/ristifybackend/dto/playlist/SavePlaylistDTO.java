package com.ristify.ristifybackend.dto.playlist;

import java.io.Serializable;

public record SavePlaylistDTO(Integer userId, String name) implements Serializable {
}