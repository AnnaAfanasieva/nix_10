package ua.com.alevel.util;

import java.util.regex.Pattern;

public final class StringToArrayUtil {

    private StringToArrayUtil() {
    }

    public static String[] stringToArrayWithSlash(String string) {
        String newInput = string.replaceAll("/", "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }

    public static String[] stringToArrayWithHyphen(String string) {
        String newInput = string.replaceAll("-", "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }

    public static String[] stringToArrayWithSpace(String string) {
        String newInput = string.replaceAll(" ", "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }

    public static String[] stringToArrayWithColon(String string) {
        String newInput = string.replaceAll(":", "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }
}
