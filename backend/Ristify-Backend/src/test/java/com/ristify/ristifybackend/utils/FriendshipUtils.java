package com.ristify.ristifybackend.utils;

import com.ristify.ristifybackend.models.Friendship;
import com.ristify.ristifybackend.models.User;

import java.sql.Timestamp;

public class FriendshipUtils {

    public static Friendship createRandomFriendship(final Integer userId) {
        return createFriendship(UserUtils.createRandomUser(userId), UserUtils.createRandomUser(), new Timestamp(System.currentTimeMillis()));
    }

    public static Friendship createRandomFriendship() {
        return createFriendship(UserUtils.createRandomUser(), UserUtils.createRandomUser(), new Timestamp(System.currentTimeMillis()));
    }

    public static Friendship createFriendship(final User userId1, final User userId2, final Timestamp createdAt) {
        return new Friendship(userId1, userId2, createdAt);
    }
}
