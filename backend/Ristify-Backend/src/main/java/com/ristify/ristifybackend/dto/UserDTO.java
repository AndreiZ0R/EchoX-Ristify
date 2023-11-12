package com.ristify.ristifybackend.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public record UserDTO(Integer id, String username, String firstName, String lastName, String email, String country, Timestamp createdAt, Date birthDate)
        implements Serializable {
}