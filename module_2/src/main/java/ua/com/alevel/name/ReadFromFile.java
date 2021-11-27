package ua.com.alevel.name;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReadFromFile {

    private static final List<String> names = new ArrayList<>();
    private static final String PATH = "files/nameInput.txt";

    public static List<String> getAllNamesFromFile() {
        names.clear();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(PATH));
            while (reader.ready()) {
                String currentString = reader.readLine().trim();
                String[] currentStrings = stringToArray(currentString);
                for (String string : currentStrings) {
                    if (!string.equals("")) {
                        names.add(string.trim());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }

    private static String[] stringToArray(String string) {
        String newInput = string.replaceAll(" ", "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }
}
