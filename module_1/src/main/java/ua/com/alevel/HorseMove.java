package ua.com.alevel;

import java.util.Scanner;

// Task 1.2
public class HorseMove {

    private static Scanner in = new Scanner(System.in);
    private final static int POSITION_A = 65;
    private final static int POSITION_H = 72;
    private static int flag;

    public static void main(String[] args) {

        int[] startPosition = new int[2];
        int[] nextPosition = new int[2];

        System.out.println("Стартовая позиция коня");
        startPosition = writePosition();

        do {

            System.out.println("Стартовая позиция коня: " + (char) startPosition[0] + startPosition[1]);

            System.out.println("\nСледующая позиция коня");
            nextPosition = writePosition();

            int[] distanceBetweenPositions = new int[2];
            distanceBetweenPositions[0] = Math.abs(nextPosition[0] - startPosition[0]);
            distanceBetweenPositions[1] = Math.abs(nextPosition[1] - startPosition[1]);


            if ((distanceBetweenPositions[0] == 2 && distanceBetweenPositions[1] == 1) || (distanceBetweenPositions[0] == 1 && distanceBetweenPositions[1] == 2)) {
                System.out.println("Позиция " + (char) nextPosition[0] + nextPosition[1] + " при следующем ходе коня - возможна\nХод выполнен");
                startPosition = nextPosition;
            } else {
                System.out.println("Позиция " + (char) nextPosition[0] + nextPosition[1] + " при следующем ходе коня - невозможна\nВы остаетесь на прежнем месте");
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

    private static int[] writePosition() {

        //Получаем самую первую позицию коня (буква)
        System.out.print("Введите координату позиции по горизонтали от A до H (с большой буквы): ");
        int bufferSymbol;
        int[] position = new int[2];

        String bufferStr = in.next();
        bufferSymbol = bufferStr.charAt(0);

        if (bufferSymbol >= POSITION_A && bufferSymbol <= POSITION_H) {
            position[0] = bufferSymbol;
        } else {
            position[0] = POSITION_A;
            System.out.println("Из-за ошибок при вводе автоматически присвоена позиция по горизонтали А");
        }

        //Получаем самую первую позицию коня (цифра)
        System.out.print("Введите координату позиции по вертикали от 1 до 8: ");

        try {
            bufferSymbol = in.nextInt();
            if (bufferSymbol >= 1 && bufferSymbol <= 8) {
                position[1] = bufferSymbol;
            } else {
                position[1] = 1;
                System.out.println("Из-за ошибок при вводе автоматически присвоена позиция по вертикали 1");
            }
        } catch (Exception e) {
            position[1] = 1;
            System.out.println("Из-за ошибок при вводе автоматически присвоена позиция по вертикали 1");
        }
        return position;

    }

}
