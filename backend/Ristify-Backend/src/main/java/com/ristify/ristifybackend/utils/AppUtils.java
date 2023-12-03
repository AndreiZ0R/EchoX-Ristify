package com.ristify.ristifybackend.utils;

import java.util.Date;
import java.util.List;

public class AppUtils {
    public static final String TEST_CONTROLLER_ENDPOINT = "api/test";
    public static final String USERS_CONTROLLER_ENDPOINT = "api/users";
    public static final String FRIENDSHIPS_CONTROLLER_ENDPOINT = "/api/friendships";
    public static final String PLAYLIST_SONG_CONTROLLER_ENDPOINT = "/api/playlistSongs";
    public static final String SONG_CONTROLLER_ENDPOINT = "/api/songs";
    public static final String PLAYLIST_CONTROLLER_ENDPOINT = "/api/playlists";
    public static final String AUTHENTICATION_CONTROLLER_ENDPOINT = "/api/auth";
    public static final String APPLICATION_JSON = "application/json";
    public static final String MESSAGE = "message";
    public static final String STATUS = "status";
    public static final String PAYLOAD = "payload";
    public static final String SUCCESS = "Success";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String BAD_TOKEN = "User must provide a valid token";
    public static final String ACCESS_DENIED = "Access denied, need higher privileges";
    public static final String JWT_START_STRING = "Bearer ";
    public static final String HOST = "http://localhost:";
    public final static String VITE_DEFAULT_HOST = "http://localhost:5173";

    public final static int SECOND = 1000;
    public final static int MINUTE = 60 * SECOND;
    public final static int HOUR = 60 * MINUTE;
    public final static int DEFAULT_JWT_EXPIRY = 24 * HOUR;

    public final static String[] WHITE_LIST_URLS = {
            "/api/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/bus/v3/api-docs/**"};
    public final static List<String> jwtFilterWhitelistedUrls = List.of("/register", "/swagger-ui", "/v3/", "/bus");


    public static String constructFailedToFetch(final Class<?> clazz) {
        return "Could not fetch " + clazz.getSimpleName() + "s";
    }

    public static String constructNotFoundMessage(Class<?> clazz, final String propertyName, final Object value) {
        return "Could not find " + clazz.getSimpleName() + " with " + propertyName + ": " + value;
    }

    public static String constructFailedSaveMessage(final Class<?> clazz) {
        return "Could not save " + clazz.getSimpleName();
    }

    public static String constructFailedDeleteMessage(final Class<?> clazz, final Object id) {
        return "Failed to delete " + clazz.getSimpleName() + "with id(s): " + id;
    }

    public static String usernameNotFound(final String username) {
        return "Username not found: " + username;
    }

    public static Date now() {
        return new Date();
    }

    public static Date nowWithDelay(final int delay) {
        return new Date(System.currentTimeMillis() + delay);
    }
}
