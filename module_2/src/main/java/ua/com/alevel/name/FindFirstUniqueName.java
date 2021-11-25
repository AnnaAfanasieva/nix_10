package ua.com.alevel.name;

import java.util.List;

public class FindFirstUniqueName {

    public static void main(String[] args) {
        List<String> names = ReadFromFile.getAllNamesFromFile();
        String uniqueName = null;
        boolean isUniqueName = true;

        for (int i = 0; i < names.size(); i++) {
            for (int j = 0; j < names.size(); j++) {
                if (names.get(i).equals(names.get(j)) && i != j) {
                    isUniqueName = false;
                    j = names.size();
                }
            }

            if (isUniqueName) {
                uniqueName = names.get(i);
                i = names.size();
            } else {
                isUniqueName = true;
            }
        }

        if (uniqueName == null) {
            System.out.println("Нет уникальных имен");
        } else {
            System.out.println("Первое уникальное имя - " + uniqueName);
        }
    }
}
