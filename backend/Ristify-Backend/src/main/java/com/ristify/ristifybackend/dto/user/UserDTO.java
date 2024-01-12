package com.ristify.ristifybackend.dto.user;

import com.ristify.ristifybackend.models.user.UserRole;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public record UserDTO(
        Integer id,
        String username,
        String firstName,
        String lastName,
        String email,
        String country,
        Timestamp createdAt,
        Date birthDate,
        UserRole role) implements Serializable {
}