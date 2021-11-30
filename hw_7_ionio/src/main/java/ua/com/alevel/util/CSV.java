package ua.com.alevel.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CSV {

    private CSV() { }

    public static void writeStringToCSVFile(String path, String stringToWrite) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path, true);
            fileWriter.write(stringToWrite + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в CSV файл");
        }
    }

    public static void cleanCSVFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write("");
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при очистке CSV файла");
        }
    }

    public static List<String> readStringListFromCSVFile(String path) {
        List<String> list = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            while (bufferedReader.ready()) {
                list.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении из CSV файла");
        }
        return list;
    }
}
