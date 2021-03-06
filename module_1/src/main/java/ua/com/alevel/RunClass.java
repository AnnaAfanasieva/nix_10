package ua.com.alevel;

import java.util.Scanner;

public class RunClass {

    private static int levelNumber = 1;

    public static void main(String[] args) {
        int selectedTask;
        Scanner in = new Scanner(System.in);

        while (levelNumber != 0) {
            System.out.println("\nВыберите Уровень:\n1 - Уровень 1\n2 - Уровень 2\n3 - Уровень 3\n0 - Выйти из программы");
            System.out.print("Ваш выбор: ");
            try {
                levelNumber = in.nextInt();
            } catch (Exception e) {
                levelNumber = 1;
                System.out.println("Из-за ошибок при вводе автоматически выбран Уровень 1");
            }

            if (levelNumber == 1) {
                selectedTask = 1;

                while (selectedTask != 0) {
                    System.out.println("\nУровень 1\nВыберите Задачу:\n1 - Задача №1\n2 - Задача №2\n3 - Задача №3\n0 - Вернуться к выбору Уровня");
                    System.out.print("Ваш выбор: ");
                    try {
                        selectedTask = in.nextInt();
                    } catch (Exception e) {
                        selectedTask = 1;
                        System.out.println("Из-за ошибок при вводе автоматически выбрана Задача №1");
                    }
                    if (selectedTask == 1) {
                        System.out.println("Уровень 1 - Задача №1");
                        //Task 1.1
                        AreaOfTriangle.main(null);
                    } else if (selectedTask == 2) {
                        System.out.println("Уровень 1 - Задача №2");
                        //Task 1.2
                        HorseMove.main(null);
                    } else if (selectedTask == 3) {
                        System.out.println("Уровень 1 - Задача №3");
                        //Task 1.3
                        CountingNumbers.main(null);
                    } else if (selectedTask == 0) {
                        System.out.println("Выход из Уровня 1");
                    } else {
                        System.out.println("Введены некорректные данные, попробуйте еще раз");
                    }
                }
            } else if (levelNumber == 2) {
                selectedTask = 1;

                while (selectedTask != 0) {
                    System.out.println("\nУровень 2\nВыберите Задачу:\n1 - Задача №1\n2 - Задача №2\n0 - Вернуться к выбору Уровня");
                    System.out.print("Ваш выбор: ");
                    try {
                        selectedTask = in.nextInt();
                    } catch (Exception e) {
                        selectedTask = 1;
                        System.out.println("Из-за ошибок при вводе автоматически выбрана Задача №1");
                    }

                    if (selectedTask == 1) {
                        System.out.println("Уровень 2 - Задача №1");
                        //Task 2.1
                        CheckingStringForValidity.main(null);
                    } else if (selectedTask == 2) {
                        System.out.println("Уровень 2 - Задача №2");
                        //Task 2.2
                        TreeRun.main(null);
                    } else if (selectedTask == 0) {
                        System.out.println("Выход из Уровня 2");
                    } else {
                        System.out.println("Введены некорректные данные, попробуйте еще раз");
                    }
                }
            } else if (levelNumber == 3) {
                selectedTask = 1;

                while (selectedTask != 0) {
                    System.out.println("\nУровень 3\nВыберите Задачу:\n1 - Задача №1\n0 - Вернуться к выбору Уровня");
                    System.out.print("Ваш выбор: ");
                    selectedTask = in.nextInt();

                    if (selectedTask == 1) {
                        System.out.println("Уровень 3 - Задача №1");
                        //Task 3.1
                        GameOfLife.main(null);
                    } else if (selectedTask == 0) {
                        System.out.println("Выход из Уровня 3");
                    } else {
                        System.out.println("Введены некорректные данные, попробуйте еще раз");
                    }
                }
            } else if (levelNumber == 0) {
                System.out.println("Работа приложения завершена");
            } else {
                System.out.println("Введены некорректные данные, попробуйте еще раз");
            }
        }
    }
}