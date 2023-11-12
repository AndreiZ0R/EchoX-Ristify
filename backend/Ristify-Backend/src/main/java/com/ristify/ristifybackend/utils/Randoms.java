package com.ristify.ristifybackend.utils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Randoms {
    public static Integer randomInteger() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static Integer randomInteger(final int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static Integer randomInteger(final int lower, final int upper) {
        return ThreadLocalRandom.current().nextInt(lower, upper);
    }

    public static Boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static String alphabetic() {
        return UUID.randomUUID().toString().replaceAll("_", "");
    }

    public static List<String> randomStringList() {
        return List.of(alphabetic(), alphabetic(), alphabetic());
    }
}
