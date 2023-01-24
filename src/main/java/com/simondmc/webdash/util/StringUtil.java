package com.simondmc.webdash.util;

import com.simondmc.webdash.server.RouteHandler;

public class StringUtil {
    /**
     * Joins a string array into a single string.
     * @param array The String array to join.
     * @param delimiter The delimiter to use between each element.
     * @param startAt The index of the first element to include in the string.
     * @return The joined String.
     */
    public static String joinStringArray(String[] array, String delimiter, int startAt) {
        StringBuilder builder = new StringBuilder();
        for (int i = startAt; i < array.length; i++) {
            builder.append(array[i]);
            if (i < array.length - 1) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }

    /**
     * Unescapes every quote in a string.
     * @param string The string to change every `\"` to `"` in.
     * @return The unescaped string.
     */
    public static String unescapeQuotes(String string) {
        return string.replaceAll("\\\\\"", "\"");
    }

    /**
     * Escapes every quote in a string.
     * @param string The string to change every `"` to `\"` in.
     * @return The escaped string.
     */
    public static String escapeQuotes(String string) {
        return string.replaceAll("\"", "\\\\\"");
    }

    /**
     * Generates an id from a name based on the following criteria:
     * 1. All spaces are replaced with underscores
     * 2. All non-alphanumeric characters are removed
     * 3. All letters are converted to lowercase
     * 4. If id already exists (RouteHandler.get(id) != null), add an underscore to the end
     * @param name The name to generate an id from.
     * @return The generated id.
     */
    public static String generateId(String name) {
        String id = name.replaceAll(" ", "_").replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        while (RouteHandler.getRoute(id) != null) {
            id += "_";
        }
        return id;
    }

    /**
     * Gets the rest of the string after first occurrence of a delimiter.
     * @param string The string to get the rest of. If the delimiter is not found, an empty string is returned.
     * @param delimiter The delimiter to find.
     * @return The rest of the string after the first occurrence of the delimiter.
     */
    public static String getRestOfString(String string, String delimiter) {
        int index = string.indexOf(delimiter);
        if (index == -1) {
            return "";
        }
        return string.substring(index + delimiter.length());
    }
}
