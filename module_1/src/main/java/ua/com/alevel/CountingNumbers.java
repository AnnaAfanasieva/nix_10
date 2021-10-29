package ua.com.alevel;

import java.util.Arrays;
import java.util.Scanner;

//Task 1.1
public class CountingNumbers {

    private static int lengthOfArray = 5;

    public static void main(String[] args) {

        int[] numbers;
        boolean continueWritingArray;
        int startTaskAgain;
        int SymbolIndex;
        Scanner in = new Scanner(System.in);

        do {
            continueWritingArray = true;
            SymbolIndex = 0;
            numbers = new int[lengthOfArray];

            while (continueWritingArray) {
                System.out.print("Введите элемент массива (для выхода введите exit): ");
                if (in.hasNextInt()) {
                    numbers[SymbolIndex] = in.nextInt();
                    SymbolIndex++;
                } else {
                    String strToExit = in.next();
                    if (strToExit.equals("exit")) {
                        System.out.println("Запись потока чисел завершена");
                        continueWritingArray = false;
                    }
                }

                if (SymbolIndex == lengthOfArray) {
                    lengthOfArray += 5;
                    numbers = Arrays.copyOf(numbers, lengthOfArray);
                }
            }

            lengthOfArray = SymbolIndex;
            numbers = Arrays.copyOf(numbers, lengthOfArray);
            int numberOfUniqueSymbols;

            if (lengthOfArray != 0) {
                numberOfUniqueSymbols = 1;
            } else {
                numberOfUniqueSymbols = 0;
            }

            boolean isUniqueSymbol = true;

            for (int i = 1; i < numbers.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (numbers[i] == numbers[j]) {
                        isUniqueSymbol = false;
                    }
                }

                if (isUniqueSymbol) {
                    numberOfUniqueSymbols++;
                }
                isUniqueSymbol = true;
            }
            System.out.println("Колличество уникальных чисел: " + numberOfUniqueSymbols);

            startTaskAgain = MenuForTaskUtil.menu();
        } while (startTaskAgain != 0);
    }
}