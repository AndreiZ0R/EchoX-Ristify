package com.ristify.ristifybackend.dto.friendship;

import java.io.Serializable;

public record SaveFriendshipDTO(Integer userId1, Integer userId2) implements Serializable {
}
