package com.ristify.ristifybackend.utils;

public class AppUtils {
    public static final String testControllerEndpoint = "api/test";
    public static final String usersControllerEndpoint = "api/users";
    public static final String APPLICATION_JSON = "application/json";
    public static final String MESSAGE = "message";
    public static final String STATUS = "status";
    public static final String PAYLOAD = "payload";
    public static final String SUCCESS = "Success";
    public static final String HOST = "http://localhost:";

    public static String constructNotFoundMessage(Class<?> clazz, final String propertyName, final Object value) {
        return "Could not find " + clazz.getSimpleName() + " with " + propertyName + ": " + value;
    }

    public static String constructFailedSaveMessage(final Class<?> clazz) {
        return "Could not save " + clazz.getSimpleName();
    }

    public static String constructFailedDeleteMessage(final Class<?> clazz, final Object object) {
        return "Failed to delete " + clazz.getSimpleName() + ": " + object;
    }

    public static String constructSuccessDeleteMessage(final Class<?> clazz, final Object id) {
        return "Successfully deleted " + clazz.getSimpleName() + ": " + id;
    }
}
