package com.epam.selenium.annotation;

public class StringReplacement {
    private StringReplacement() {
    }

    public static String replaceWithValues(final String target, final String replaceToken, final String... values) {
        return String.format(target.replace("%", "%%").replace(replaceToken, "%s"), (Object[]) values);
    }
}
