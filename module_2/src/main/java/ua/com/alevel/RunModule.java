package ua.com.alevel;

import ua.com.alevel.cities.FindingTheCheapestWay;
import ua.com.alevel.date.TransformDates;
import ua.com.alevel.name.FindFirstUniqueName;

public class RunModule {
    public static void main(String[] args) {
        String separator = "_________________________________________________________________";
        System.out.println(separator + "\nTask 1");
        TransformDates.main(null);
        System.out.println("Task 1 выполнен. Проверьте файл \"dateOutput.txt\"");
        System.out.println(separator + "\nTask 2");
        FindFirstUniqueName.main(null);
        System.out.println(separator + "\nTask 3");
        FindingTheCheapestWay.main(null);
    }
}
