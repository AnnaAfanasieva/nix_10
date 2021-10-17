package ua.com.alevel;

import java.util.Scanner;

//Task 1
public class DigitCounter {

    private static int sumOfDigits(String string) {
        char[] charSet = string.toCharArray();
        int counter = 0;
        for (int i = 0; i < charSet.length; i++) {
            if (Character.isDigit(charSet[i])) {
                counter += Character.getNumericValue(charSet[i]);
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String string = in.next();

        int sumOfDigits = sumOfDigits(string);
        System.out.println("Сумма цифр в строке = " + sumOfDigits);

    }

}
