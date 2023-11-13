package com.ristify.ristifybackend.dto.playlist;

import com.ristify.ristifybackend.dto.user.UserDTO;

import java.io.Serializable;

public record PlaylistDTO(UserDTO user, String name)
        implements Serializable {
}