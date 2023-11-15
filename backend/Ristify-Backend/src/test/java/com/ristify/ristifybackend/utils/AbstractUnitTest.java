package com.ristify.ristifybackend.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractUnitTest<T> {
    public void assertThatFails() {
        fail("Should have valid user data");
    }

    public void assertThatFails(final Object object) {
        fail("Should not contain: " + object);
    }

    public void assertThatItemsAreEqual(final T actual, final T expected) {
        String actualBuilder = new ReflectionToStringBuilder(actual, ToStringStyle.JSON_STYLE).toString();
        String expectedBuilder = new ReflectionToStringBuilder(expected, ToStringStyle.JSON_STYLE).toString();
        assertThat(actualBuilder, equalTo(expectedBuilder));
    }

    public void assertThatItemsAreEqual(final List<T> actual, final List<T> expected) {
        String actualBuilder = actual.stream()
                .map(param -> new ReflectionToStringBuilder(param, ToStringStyle.JSON_STYLE).toString())
                .collect(Collectors.joining());
        String expectedBuilder = expected.stream()
                .map(param -> new ReflectionToStringBuilder(param, ToStringStyle.JSON_STYLE).toString())
                .collect(Collectors.joining());

        assertThat(actualBuilder, equalTo(expectedBuilder));
    }
}