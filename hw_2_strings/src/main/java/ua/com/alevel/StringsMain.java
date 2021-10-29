package ua.com.alevel;

import java.util.Scanner;

public class StringsMain {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int selectReverse;
        String newLine;
        boolean isNotFirstIteration = false;
        int startTaskAgain;

        do {

            System.out.print("Введите строку: ");
            if (isNotFirstIteration) {
                in.nextLine();
            }
            isNotFirstIteration = true;
            newLine = in.nextLine();

            System.out.println("Выберите реверс, которым хотите воспользоваться");
            System.out.println("1 - обычный реверс\n2 - реверс по указанной подстроке в строке");
            System.out.println("3 - реверс с указанием начального и конечного символа или строки");
            System.out.println("4 - реверс с указанием начального и конечного индекса");
            try {
                selectReverse = in.nextInt();
                if (selectReverse < 1 || selectReverse > 5) {
                    System.out.println("Из-за неправильного ввода по умолчанию выбран первый вариант");
                    selectReverse = 1;
                }
            } catch (Exception e) {
                System.out.println("Из-за неправильного ввода по умолчанию выбран первый вариант");
                selectReverse = 1;
            }


            if (selectReverse == 1) {
                newLine = ReverseStringUtil.reverse(newLine);
            } else if (selectReverse == 2) {
                System.out.print("Введите подстроку: ");
                in.nextLine();
                String subStr = in.nextLine();
                newLine = ReverseStringUtil.reverse(newLine, subStr);
            } else if (selectReverse == 3) {
                System.out.print("Введите начальный символ или строку: ");
                in.nextLine();
                String startSymbol = in.nextLine();
                System.out.print("Введите конечный символ или строку: ");
                String endSymbol = in.nextLine();
                newLine = ReverseStringUtil.reverse(newLine, startSymbol, endSymbol);
            } else if (selectReverse == 4) {
                System.out.print("Введите начальный индекс: ");
                int startIndex;
                try {
                    startIndex = in.nextInt();
                    if (startIndex < 0 || startIndex >= newLine.length()) {
                        startIndex = 0;
                        System.out.println("Индекс не принадлежит строке, по умолчанию присвоено значение 0");
                    }
                } catch (Exception e) {
                    startIndex = 0;
                    System.out.println("Индекс не принадлежит строке, по умолчанию присвоено значение 0");
                }

                System.out.print("Введите конечный индекс: ");
                int endIndex;
                try {
                    endIndex = in.nextInt();
                    if (endIndex < 0 || endIndex >= newLine.length()) {
                        endIndex = newLine.length() - 1;
                        System.out.println("Индекс не принадлежит строке, по умолчанию присвоено значение " + endIndex);
                    }
                } catch (Exception e) {
                    endIndex = newLine.length() - 1;
                    System.out.println("Индекс не принадлежит строке, по умолчанию присвоено значение " + endIndex);
                }
                newLine = ReverseStringUtil.reverse(newLine, startIndex, endIndex);
            }

            System.out.println("Строка теперь равна " + newLine);

            System.out.println("\nВыполнить задачу еще раз?\n1 - Да\n0 - Нет");
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
        } while (startTaskAgain != 0);
    }
}