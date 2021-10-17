package ua.com.alevel;

import java.util.Scanner;

//Task 3
public class LessonTime {

    private static final int startTimeOfLessons = 540;
    private static final int lessonDuration = 45;
    private static final int minBreak = 5;

    private static final int hour = 60;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер урока от 1 до 10: ");
        int lessonNumber = in.nextInt();
        timeOfLesson(lessonNumber);

    }

    private static void timeOfLesson(int lessonNumber) {

        int time = startTimeOfLessons + lessonDuration * lessonNumber + (lessonNumber - 1) * minBreak + (lessonNumber / 2) * 10 - ((lessonNumber - 1) % 2) * 10;

        int lessonEndHour = time / hour;
        int lessonEndMinutes = time % 60;

        System.out.println("Время окончания урока " + lessonEndHour + ":" + lessonEndMinutes);

    }

}
