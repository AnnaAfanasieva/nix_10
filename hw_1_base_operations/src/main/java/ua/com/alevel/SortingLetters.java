package ua.com.alevel;

import java.util.Arrays;
import java.util.Scanner;

//Task 2
public class SortingLetters {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String string = in.next();
        sortArray(string);

    }

    private static void sortArray(String string) {
        char[] arrFromString = string.toCharArray();

        //Удаление ненужных символов
        for (int i = 0; i < arrFromString.length; i++) {
            if (arrFromString[i] < 65 || (arrFromString[i] > 90 && arrFromString[i] < 97) || (arrFromString[i] > 122 && arrFromString[i] < 1040) || arrFromString[i] > 1103) {
                arrFromString[i] = 0;
            }
        }

        //Сортировка массива
        Arrays.sort(arrFromString);

        //Цикл подсчета и вывода результата на экран
        for (int i = 0; i < arrFromString.length; i++) {
            if (arrFromString[i] != 0) {
                int counter = 0;
                int index = i;
                int j = i;
                while (j != arrFromString.length && arrFromString[j] == arrFromString[index]) {
                    counter++;
                    j++;
                }

                System.out.println(arrFromString[i] + " - " + counter);
                i = j - 1;

            }
        }

    }

}
