package ua.com.alevel;

import java.util.Scanner;

// Task 1.2
public class HorseMove {

    private final static int POSITION_A = 65;
    private final static int POSITION_H = 72;

    public static void main(String[] args) {

        int[] startPosition;
        int[] nextPosition;
        int startTaskAgain;

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

            startTaskAgain = MenuForTaskUtil.menu();
        } while (startTaskAgain != 0);
    }

    private static int[] writePosition() {

        System.out.print("Введите координату позиции по горизонтали от A до H (с большой буквы): ");
        int numberOfLetterCoordinate;
        int[] position = new int[2];

        Scanner in = new Scanner(System.in);
        String letterCoordinate = in.next();
        numberOfLetterCoordinate = letterCoordinate.charAt(0);

        if (numberOfLetterCoordinate >= POSITION_A && numberOfLetterCoordinate <= POSITION_H) {
            position[0] = numberOfLetterCoordinate;
        } else {
            position[0] = POSITION_A;
            System.out.println("Из-за ошибок при вводе автоматически присвоена позиция по горизонтали А");
        }

        int numericCoordinate;
        System.out.print("Введите координату позиции по вертикали от 1 до 8: ");
        try {
            numericCoordinate = in.nextInt();
            if (numericCoordinate >= 1 && numericCoordinate <= 8) {
                position[1] = numericCoordinate;
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