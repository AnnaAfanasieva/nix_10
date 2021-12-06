package ua.com.alevel.util;

import ua.com.alevel.Date;

import java.util.Arrays;
import java.util.List;

public class CalendarUtil {

    //TODO принимать строку, искать формат её даты и возвращать объект Calendar
    public static Date createDateFromString(String date) {

        return null;
    }

    public static void sortDates(List<Date> dates, int inputOutputType, boolean isSortAsc) {
        long[] datesInMilliseconds = new long[dates.size()];
        for (int i = 0; i < dates.size(); i++) {
            datesInMilliseconds[i] = dates.get(i).getMilliseconds();
        }
        Arrays.sort(datesInMilliseconds);
        if (isSortAsc) {
            for (int i = 0; i < dates.size(); i++) {
                Date date = new Date(datesInMilliseconds[i]);
                System.out.println((i + 1) + " - " + date.showDate(inputOutputType));
            }
        } else {
            int iter = 1;
            for (int i = dates.size() - 1; i >= 0; i--) {
                Date date = new Date(datesInMilliseconds[i]);
                System.out.println(iter + " - " + date.showDate(inputOutputType));
                iter++;
            }
        }
    }

    private static long getMilliseconds(int year, int month, int day, int hours, int minutes, int seconds, int milliseconds) {
        int numberOfLeapYears = year / 4;
        long dateInMilliseconds = numberOfLeapYears * Date.FOUR_YEARS;
        int numberOfNotLeapYears = year % 4;
        dateInMilliseconds += numberOfNotLeapYears * Date.NOT_LEAP_YEARS;
        for (int i = 1; i <= month; i++) {
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dateInMilliseconds += Date.DAYS_31;
                    break;
                case 2:
                    if (isLeapYear(year)) {
                        dateInMilliseconds += Date.DAYS_29;
                    } else {
                        dateInMilliseconds += Date.DAYS_28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dateInMilliseconds += Date.DAYS_30;
                    break;
            }
        }
        dateInMilliseconds += day * Date.DAY + hours * Date.HOUR + minutes * Date.MINUTE + seconds * Date.SECOND + milliseconds;
        return dateInMilliseconds;
    }

    public static boolean isLeapYear(int yearInDays) {
        if (yearInDays % 4 != 0) {
            return false;
        } else if (yearInDays % 100 != 0) {
            return true;
        } else {
            return yearInDays % 400 == 0;
        }
    }

    // ^\d{0,2}\/\d{0,2}\/\d{0,4}$  -  dd/mm/yyyy или mm/dd/yyyy
    // ^\d{0,2}\-\d{0,2}\-\d{0,4}$  -  dd-mm-yyyy или mm-dd-yyyy

    // ^\d{0,2}(\/|\-)\d{0,2}(\/|\-)\d{0,4}$  -  dd/mm/yyyy или mm/dd/yyyy или dd-mm-yyyy или mm-dd-yyyy

    // также подойдет запись //


    // -> сравнение: передавать asc или desc сортировку
    // -> все даты переводить в миллисекунды
    // -> отсортировать по миллисекундам

}
