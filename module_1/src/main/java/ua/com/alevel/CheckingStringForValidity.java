package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckingStringForValidity {

    public static void main(String[] args) {

        int flag;
        Scanner in = new Scanner(System.in);
        String bufferStr;
        char[] arrayStr;
        List charStack;

        do {
            System.out.print("Введите строку: ");
            bufferStr = in.next();
            arrayStr = bufferStr.toCharArray();
            charStack = new ArrayList();
            int charIndex = 0;
            boolean flagStringValidity = true;

            for (int i = 0; i < arrayStr.length; i++) {
                if (arrayStr[i] == '(' || arrayStr[i] == '{' || arrayStr[i] == '[') {
                    charStack.add(charIndex, arrayStr[i]);
                    charIndex++;
                } else if ((arrayStr[i] == ')' && charStack.get(charIndex - 1).equals('(')) || (arrayStr[i] == '}' && charStack.get(charIndex - 1).equals('{')) || (arrayStr[i] == ']' && charStack.get(charIndex - 1).equals('['))) {
                    charIndex -= 1;
                    charStack.remove(charIndex);
                } else if (arrayStr[i] == ')' || arrayStr[i] == '}' || arrayStr[i] == ']') {
                    i = arrayStr.length;
                    flagStringValidity = false;
                }
            }

            if (charIndex == 0 && flagStringValidity) {
                System.out.println("Строка допустима");
            } else {
                System.out.println("Строка недопустима");
            }

            System.out.println("\nВыполнить задачу еще раз?\n1 - Да\n0 - Нет");
            try {
                flag = in.nextInt();
                if (flag != 1 && flag != 0) {
                    flag = 0;
                    System.out.println("Автоматический выход из задачи");
                }
            } catch (Exception e) {
                System.out.println("Автоматический выход из задачи");
                flag = 0;
            }
        } while (flag != 0);
    }
}


