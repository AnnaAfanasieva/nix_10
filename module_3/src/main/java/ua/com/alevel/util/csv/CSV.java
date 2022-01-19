package ua.com.alevel.util.csv;

import java.io.FileWriter;
import java.io.IOException;

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
        String header = "Transaction number,Created,User,Category,Sum\n";
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(header);
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при очистке CSV файла");
        }
    }
}
