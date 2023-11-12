package com.ristify.ristifybackend.utils;

import com.ristify.ristifybackend.models.User;

import java.sql.Timestamp;
import java.util.Date;

public class UserUtils {

    public static User createRandomUser(final String username) {
        User user = createRandomUser();
        user.setUsername(username);
        return user;
    }

    public static User createRandomUser() {
        return createUser(
                Randoms.randomInteger(0, 1000),
                Randoms.alphabetic(),
                Randoms.alphabetic(),
                Randoms.alphabetic(),
                Randoms.alphabetic(),
                Randoms.alphabetic() + "@gmail.com",
                Randoms.alphabetic(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Date());
    }

    public static User createUser(
            final Integer userId,
            final String username,
            final String password,
            final String firstName,
            final String lastName,
            final String email,
            final String country,
            final Timestamp createdAt,
            final Timestamp lastLogin,
            final Date birthDate) {
        return new User(userId, username, password, firstName, lastName, email, country, createdAt, lastLogin, birthDate);
    }
}
