package com.ristify.ristifybackend.dto.friendship;

import java.io.Serializable;

public record CreateFriendshipRequest(Integer userId1, Integer userId2) implements Serializable {
}
