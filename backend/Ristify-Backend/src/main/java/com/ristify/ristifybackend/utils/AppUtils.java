package com.ristify.ristifybackend.utils;

public class AppUtils {
    public static final String testControllerEndpoint = "api/test";
    public static final String usersControllerEndpoint = "api/users";
    public static final String friendshipsControllerEndpoint = "/api/friendships";
    public static final String playlistSongControllerEndpoint = "/api/playlistSongs";
    public static final String songControllerEndpoint = "api/songs";
    public static final String playlistControllerEndpoint = "api/playlists";
    public static final String APPLICATION_JSON = "application/json";
    public static final String MESSAGE = "message";
    public static final String STATUS = "status";
    public static final String PAYLOAD = "payload";
    public static final String SUCCESS = "Success";
    public static final String HOST = "http://localhost:";

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
}
