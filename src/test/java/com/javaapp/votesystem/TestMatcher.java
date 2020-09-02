package com.javaapp.votesystem;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TestMatcher<T> {
    private final String[] fieldsToIgnore;

    private TestMatcher(String... fieldsToIgnore) {
        this.fieldsToIgnore = fieldsToIgnore;
    }

    public static <T> com.javaapp.votesystem.TestMatcher<T> usingFieldsComparator(String... fieldsToIgnore) {
        return new com.javaapp.votesystem.TestMatcher<>(fieldsToIgnore);
    }

    public void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, fieldsToIgnore);
    }

    public void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, List.of(expected));
    }

    public void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields(fieldsToIgnore).isEqualTo(expected);
    }
}
