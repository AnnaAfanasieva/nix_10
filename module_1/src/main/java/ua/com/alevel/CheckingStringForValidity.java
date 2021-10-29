package ua.com.alevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Task 2.1
public class CheckingStringForValidity {

    public static void main(String[] args) {

        int startTaskAgain;
        String stringToArray;
        char[] arrayString;
        List charStack;

        do {
            System.out.print("Введите строку: ");
            Scanner in = new Scanner(System.in);
            stringToArray = in.next();
            arrayString = stringToArray.toCharArray();
            charStack = new ArrayList();
            int charIndex = 0;
            boolean flagStringValidity = true;

            for (int i = 0; i < arrayString.length; i++) {
                if (i < arrayString.length && (arrayString[i] == '(' || arrayString[i] == '{' || arrayString[i] == '[')) {
                    charStack.add(charIndex, arrayString[i]);
                    charIndex++;
                } else if ((i < arrayString.length && charIndex != 0) && ((arrayString[i] == ')' && charStack.get(charIndex - 1).equals('(')) || (arrayString[i] == '}' && charStack.get(charIndex - 1).equals('{')) || (arrayString[i] == ']' && charStack.get(charIndex - 1).equals('[')))) {
                    charIndex -= 1;
                    charStack.remove(charIndex);
                } else if (arrayString[i] == ')' || arrayString[i] == '}' || arrayString[i] == ']') {
                    i = arrayString.length;
                    flagStringValidity = false;
                }
            }

            if (charIndex == 0 && flagStringValidity) {
                System.out.println("Строка допустима");
            } else {
                System.out.println("Строка недопустима");
            }
            startTaskAgain = MenuForTaskUtil.menu();
        } while (startTaskAgain != 0);
    }
}


