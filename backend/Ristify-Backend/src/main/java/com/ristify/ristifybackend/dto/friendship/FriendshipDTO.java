package com.ristify.ristifybackend.dto.friendship;

import com.ristify.ristifybackend.dto.user.UserDTO;

import java.io.Serializable;
import java.sql.Timestamp;

public record FriendshipDTO(UserDTO firstUser, UserDTO secondUser, Timestamp createdAt) implements Serializable {
}
