package com.ristify.ristifybackend.utils;

import com.ristify.ristifybackend.models.user.User;
import com.ristify.ristifybackend.models.user.UserRole;

import java.sql.Date;
import java.sql.Timestamp;

public class UserUtils {

    public static User createRandomUser(final Integer id) {
        User user = createRandomUser();
        user.setUserId(id);
        return user;
    }

    public static User createRandomUser(final String username) {
        User user = createRandomUser();
        user.setUsername(username);
        return user;
    }

    public static User createRandomUserWithCountry(final String country) {
        User user = createRandomUser();
        user.setCountry(country);
        return user;
    }

    public static User createRandomUserBetween(final String startTime, final String endTime) {
        long offset = Timestamp.valueOf(startTime).getTime();
        long end = Timestamp.valueOf(endTime).getTime();
        long diff = end - offset + 1;
        Timestamp randomTimestamp = new Timestamp(offset + (long) (Math.random() * diff));

        User user = createRandomUser();
        user.setCreatedAt(randomTimestamp);
        return user;
    }

    public static User createRandomUserBetween(final int startTime, final int endTime) {
        long diff = endTime - startTime + 1;
        Timestamp randomTimestamp = new Timestamp(startTime + (long) (Math.random() * diff));

        User user = createRandomUser();
        user.setCreatedAt(randomTimestamp);
        return user;
    }

    public static User createRandomUser() {
        return createUser(
                Randoms.randomPositiveInteger(),
                Randoms.alphabetic(),
                Randoms.alphabetic(),
                Randoms.alphabetic(),
                Randoms.alphabetic(),
                Randoms.alphabetic() + "@gmail.com",
                Randoms.alphabetic(),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()));
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
        return new User(userId, username, password, firstName, lastName, email, country, createdAt, lastLogin, birthDate, UserRole.User);
    }
}
