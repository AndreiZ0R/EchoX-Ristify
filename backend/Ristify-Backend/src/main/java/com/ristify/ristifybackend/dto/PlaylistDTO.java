package com.ristify.ristifybackend.dto;

import java.io.Serializable;

public record PlaylistDTO(UserDTO user, String name)
        implements Serializable {
}