package ua.com.alevel;

import java.util.Scanner;

//Task 1.3
public class AreaOfTriangle {

    private static int[] A = new int[3];
    private static int[] B = new int[3];
    private static int[] C = new int[3];
    private static int x;
    private static int y;
    private static int z;
    private static double[] triangleSides = new double[3];
    private static int flag;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        do {

            System.out.println("Координаты вершины А: ");
            A = inputCoordinates();
            System.out.println("Координаты вершины B: ");
            B = inputCoordinates();
            System.out.println("Координаты вершины C: ");
            C = inputCoordinates();
            triangleSides[0] = distanceBetweenPoints(A, B); //длина стороны АВ
            triangleSides[1] = distanceBetweenPoints(B, C); //длина стороны ВC
            triangleSides[2] = distanceBetweenPoints(A, C); //длина стороны АC
            double p = (triangleSides[0] + triangleSides[1] + triangleSides[2]) / 2; //полупериметр АВС
            double square = Math.sqrt(p * (p - triangleSides[0]) * (p - triangleSides[1]) * (p - triangleSides[2]));
            System.out.println("Площадь треугольника = " + square);

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

    private static int[] inputCoordinates() {

        System.out.print("Введите координату по Х: ");
        x = in.nextInt();
        System.out.print("Введите координату по Y: ");
        y = in.nextInt();
        System.out.print("Введите координату по Z: ");
        z = in.nextInt();
        return new int[]{x, y, z};
    }

    private static double distanceBetweenPoints(int[] A1, int[] A2) {
        return Math.sqrt(Math.pow((A1[0] - A2[0]), 2) + Math.pow((A1[1] - A2[1]), 2) + Math.pow((A1[2] - A2[2]), 2));
    }

}
