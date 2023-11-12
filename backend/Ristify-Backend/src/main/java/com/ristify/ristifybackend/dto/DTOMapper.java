package com.ristify.ristifybackend.dto;

import com.ristify.ristifybackend.models.User;

public class DTOMapper {
    public static UserDTO mapUserToDTO(final User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCountry(),
                user.getCreatedAt(),
                user.getBirthDate());
    }
}
