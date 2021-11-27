package ua.com.alevel.date;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReadFromFile {

    private static final List<String> names = new ArrayList<>();
    private static final String PATH = "files/dateInput.txt";

    public static List<String> getDatesFromFile() {
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
