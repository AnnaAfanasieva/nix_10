package ua.com.alevel;

import java.util.Arrays;
import java.util.Scanner;

//Task 1.1
public class CountingNumbers {

    private static int[] numbers = new int[]{};
    private static int lengthOfArray = 5;
    private static Scanner in = new Scanner(System.in);
    private static int flagToStart;

    public static void main(String[] args) {

        boolean flag;
        int counter;

        //Пользовательский ввод, проверка
        do {

            flag = true;
            counter = 0;

            numbers = new int[lengthOfArray];

            while (flag) {
                System.out.print("Введите элемент массива (для выхода введите exit): ");
                if (in.hasNextInt()) {
                    numbers[counter] = in.nextInt();
                    counter++;
                } else {
                    String strToExit = in.next();
                    if (strToExit.equals("exit")) {
                        System.out.println("Запись потока чисел завершена");
                        flag = false;
                    }
                }

                if (counter == lengthOfArray) {
                    lengthOfArray += 5;
                    numbers = Arrays.copyOf(numbers, lengthOfArray);
                }
            }

            lengthOfArray = counter;
            numbers = Arrays.copyOf(numbers, lengthOfArray);
            if (lengthOfArray != 0) {
                counter = 1;
            } else {
                counter = 0;
            }
            flag = true;

            //Подсчет колличества уникальных символов
            for (int i = 1; i < numbers.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (numbers[i] == numbers[j]) {
                        flag = false;
                    }
                }

                if (flag) {
                    counter++;
                }
                flag = true;

            }

            System.out.println("Колличество уникальных чисел: " + counter);

            System.out.println("\nВыполнить задачу еще раз?\n1 - Да\n0 - Нет");
            try {
                flagToStart = in.nextInt();
                if (flagToStart != 1 && flagToStart != 0) {
                    flagToStart = 0;
                    System.out.println("Автоматический выход из задачи");
                }
            } catch (Exception e) {
                System.out.println("Автоматический выход из задачи");
                flagToStart = 0;
            }


        } while (flagToStart != 0);


    }

}
