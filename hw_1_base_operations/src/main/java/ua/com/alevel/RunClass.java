package ua.com.alevel;

import java.util.Scanner;

public class RunClass {

    public static void main(String[] args) {
        int flag = 1;
        Scanner in = new Scanner(System.in);

        while (flag != 0) {
            System.out.println("\n0 - выйти из программы");
            System.out.println("1 - запустить Task1");
            System.out.println("2 - запустить Task2");
            System.out.println("3 - запустить Task3");
            System.out.println("Выберите действие: ");
            flag = in.nextInt();
            if (flag == 1) {
                //Task1
                DigitCounter digitCounter = new DigitCounter();
                digitCounter.main(null);
            } else if (flag == 2) {
                //Task2
                SortingLetters sortingLetters = new SortingLetters();
                sortingLetters.main(null);
            } else if (flag == 3) {
                //Task3
                LessonTime lessonTime = new LessonTime();
                lessonTime.main(null);
            } else if (flag != 0) {
                //Exit
                System.out.println("Вы ввели некоректные данные");
            }


        }

        System.out.println("\nРабота приложения завершена");
    }

}
