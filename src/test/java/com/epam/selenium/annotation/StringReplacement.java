package com.epam.selenium.annotation;

public class StringReplacement {
    private StringReplacement() {
    }

    public static String replaceWithValues(final String replacedToken, final String replacementToken, final String... values) {
        return String.format(replacedToken.replace("%", "%%").replace(replacementToken, "%s"), (Object[]) values);
    }
}
