package ua.com.alevel;

import java.util.Scanner;

//Task 1.3
public class AreaOfTriangle {

    public static void main(String[] args) {

        int startTaskAgain;

        do {
            System.out.println("Координаты вершины А: ");
            int[] A = inputCoordinates();
            System.out.println("Координаты вершины B: ");
            int[] B = inputCoordinates();
            System.out.println("Координаты вершины C: ");
            int[] C = inputCoordinates();
            double[] triangleSides = new double[3];
            triangleSides[0] = distanceBetweenPoints(A, B); //длина стороны АВ
            triangleSides[1] = distanceBetweenPoints(B, C); //длина стороны ВC
            triangleSides[2] = distanceBetweenPoints(A, C); //длина стороны АC
            double p = (triangleSides[0] + triangleSides[1] + triangleSides[2]) / 2; //полупериметр АВС
            double square = Math.sqrt(p * (p - triangleSides[0]) * (p - triangleSides[1]) * (p - triangleSides[2]));
            System.out.println("Площадь треугольника = " + square);

            startTaskAgain = MenuForTaskUtil.menu();
            System.out.println();
        } while (startTaskAgain != 0);
    }

    private static int[] inputCoordinates() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите координату по Х: ");
        int x = in.nextInt();
        System.out.print("Введите координату по Y: ");
        int y = in.nextInt();
        System.out.print("Введите координату по Z: ");
        int z = in.nextInt();
        return new int[]{x, y, z};
    }

    private static double distanceBetweenPoints(int[] A1, int[] A2) {
        return Math.sqrt(Math.pow((A1[0] - A2[0]), 2) + Math.pow((A1[1] - A2[1]), 2) + Math.pow((A1[2] - A2[2]), 2));
    }

}
