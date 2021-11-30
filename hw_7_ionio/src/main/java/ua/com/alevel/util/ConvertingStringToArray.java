package ua.com.alevel.util;

import java.util.regex.Pattern;

public final class ConvertingStringToArray {

    private static final String SEPARATOR = ",";

    private ConvertingStringToArray() { }

    public static String[] stringToArray(String string) {
        String newInput = string.replaceAll(SEPARATOR, "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }
}
