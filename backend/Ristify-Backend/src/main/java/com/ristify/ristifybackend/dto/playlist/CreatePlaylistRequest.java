package com.ristify.ristifybackend.dto.playlist;

import java.io.Serializable;

public record CreatePlaylistRequest(Integer userId, String name) implements Serializable {
}