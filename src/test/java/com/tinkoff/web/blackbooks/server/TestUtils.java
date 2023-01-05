package com.tinkoff.web.blackbooks.server;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.UUID;

public class TestUtils {

    public static Matcher<Object> UUID_MATCHER = new BaseMatcher<>() {
        @Override
        public void describeTo(Description description) {
            description.appendText("correct UUID");
        }

        @Override
        public boolean matches(Object actual) {
            if (actual instanceof UUID) {
                return true;
            }
            try {
                UUID ignored = UUID.fromString((String) actual);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    };
}
