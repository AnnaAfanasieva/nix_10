package ua.com.alevel;

import java.util.Scanner;

public final class MenuForTaskUtil {

    private MenuForTaskUtil () { }

    public static int menu () {
        int startTaskAgain;
        System.out.println("\nВыполнить задачу еще раз?\n1 - Да\n0 - Нет");
        Scanner in = new Scanner(System.in);
        try {
            startTaskAgain = in.nextInt();
            if (startTaskAgain != 1 && startTaskAgain != 0) {
                startTaskAgain = 0;
                System.out.println("Автоматический выход из задачи");
            }
        } catch (Exception e) {
            System.out.println("Автоматический выход из задачи");
            startTaskAgain = 0;
        }
        return startTaskAgain;
    }
}
