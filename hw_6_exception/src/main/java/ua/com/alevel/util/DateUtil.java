package ua.com.alevel.util;

import ua.com.alevel.Date;

import java.util.Arrays;
import java.util.List;

public final class DateUtil {

    private DateUtil() { }

    private static final String REGEX_FIRST_TYPE_WITH_SLASH_SEPARATOR = "^\\d{0,2}\\/\\d{0,2}\\/\\d{0,4}$";
    private static final String REGEX_SECOND_TYPE_WITH_SLASH_SEPARATOR = "^\\d{0,2}\\/\\d{0,2}\\/\\d{0,4}$";
    private static final String REGEX_THIRD_TYPE_WITH_SLASH_SEPARATOR = "^\\w{0,9}\\/\\d{0,2}\\/\\d{0,4}$";
    private static final String REGEX_WITH_TIME_TYPE_WITH_SLASH_SEPARATOR = "^\\d{0,2}\\/\\w{0,9}\\/\\d{0,4}\\s\\d{0,2}\\:\\d{0,2}$";

    private static final String REGEX_FIRST_TYPE_WITH_HYPHEN_SEPARATOR = "^\\d{0,2}\\-\\d{0,2}\\-\\d{0,4}$";
    private static final String REGEX_SECOND_TYPE_WITH_HYPHEN_SEPARATOR = "^\\d{0,2}\\-\\d{0,2}\\-\\d{0,4}$";
    private static final String REGEX_THIRD_TYPE_WITH_HYPHEN_SEPARATOR = "^\\w{0,9}\\-\\d{0,2}\\-\\d{0,4}$";
    private static final String REGEX_WITH_TIME_TYPE_WITH_HYPHEN_SEPARATOR = "^\\d{0,2}\\-\\w{0,9}\\-\\d{0,4}\\s\\d{0,2}\\:\\d{0,2}$";

    public static Date createDateFromString(String date, int type) {
        int[] dateElements;
        long milliseconds = 0;
        if ((type == 1 && date.matches(REGEX_FIRST_TYPE_WITH_SLASH_SEPARATOR)) || (type == 5 && date.matches(REGEX_FIRST_TYPE_WITH_HYPHEN_SEPARATOR))) {
            dateElements = convertStringDateToArrayFirstType(date, type);
            try {
                milliseconds = getMilliseconds(dateElements[0], dateElements[1], dateElements[2], 0, 0, 0, 0);
            } catch (Exception ignored) {
                return null;
            }
        } else if ((type == 2 && date.matches(REGEX_SECOND_TYPE_WITH_SLASH_SEPARATOR)) || (type == 6 && date.matches(REGEX_SECOND_TYPE_WITH_HYPHEN_SEPARATOR))) {
            dateElements = convertStringDateToArraySecondType(date, type);
            try {
                milliseconds = getMilliseconds(dateElements[0], dateElements[1], dateElements[2], 0, 0, 0, 0);
            } catch (Exception ignored) {
                return null;
            }
        } else if ((type == 3 && date.matches(REGEX_THIRD_TYPE_WITH_SLASH_SEPARATOR)) || (type == 7 && date.matches(REGEX_THIRD_TYPE_WITH_HYPHEN_SEPARATOR))) {
            dateElements = convertStringDateToArrayThirdType(date, type);
            try {
                milliseconds = getMilliseconds(dateElements[0], dateElements[1], dateElements[2], 0, 0, 0, 0);
            } catch (Exception ignored) {
                return null;
            }
        } else if ((type == 4 && date.matches(REGEX_WITH_TIME_TYPE_WITH_SLASH_SEPARATOR)) || (type == 8 && date.matches(REGEX_WITH_TIME_TYPE_WITH_HYPHEN_SEPARATOR))) {
            dateElements = convertStringDateToArrayWithTimeType(date, type);
            try {
                milliseconds = getMilliseconds(dateElements[0], dateElements[1], dateElements[2], dateElements[3], dateElements[4], 0, 0);
            } catch (Exception ignored) {
                return null;
            }
        } else {
            return null;
        }
        return new Date(milliseconds);
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
        dateInMilliseconds += numberOfNotLeapYears * Date.NOT_LEAP_YEAR;
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

    private static boolean isLeapYear(int yearInDays) {
        if (yearInDays % 4 != 0) {
            return false;
        } else if (yearInDays % 100 != 0) {
            return true;
        } else {
            return yearInDays % 400 == 0;
        }
    }

    private static int[] convertStringDateToArrayFirstType(String date, int type) {
        String[] elementsInString = new String[0];
        if (type == 1) {
            elementsInString = StringToArrayUtil.stringToArrayWithSlash(date);
        } else if (type == 5) {
            elementsInString = StringToArrayUtil.stringToArrayWithHyphen(date);
        }

        int day = 1;
        int month = 1;
        int year = 0;
        if (elementsInString.length == 0 && (date.matches("^\\/\\/$") || date.matches("^\\-\\-$"))) {
            return new int[]{year, month, day};
        }

        try {
            if (!elementsInString[0].equals("")) {
                day = Integer.parseInt(elementsInString[0]);
            }
            if (!elementsInString[1].equals("")) {
                month = Integer.parseInt(elementsInString[1]);
            }
            if (!elementsInString[2].equals("")) {
                year = Integer.parseInt(elementsInString[2]);
            }
        } catch (Exception e) {
            return null;
        }
        if (isValidDate(day, month, year)) {
            return new int[]{year, month, day};
        } else {
            return null;
        }
    }

    private static int[] convertStringDateToArraySecondType(String date, int type) {
        String[] elementsInString = new String[0];
        if (type == 2) {
            elementsInString = StringToArrayUtil.stringToArrayWithSlash(date);
        } else if (type == 6) {
            elementsInString = StringToArrayUtil.stringToArrayWithHyphen(date);
        }
        int day = 1;
        int month = 1;
        int year = 0;
        if (elementsInString.length == 0 && (date.matches("^\\/\\/$") || date.matches("^\\-\\-$"))) {
            return new int[]{year, month, day};
        }
        try {
            if (!elementsInString[1].equals("")) {
                day = Integer.parseInt(elementsInString[1]);
            }
            if (!elementsInString[0].equals("")) {
                month = Integer.parseInt(elementsInString[0]);
            }
            if (!elementsInString[2].equals("")) {
                year = Integer.parseInt(elementsInString[2]);
            }
        } catch (Exception e) {
            return null;
        }
        if (isValidDate(day, month, year)) {
            return new int[]{year, month, day};
        } else {
            return null;
        }
    }

    private static int[] convertStringDateToArrayThirdType(String date, int type) {
        String[] elementsInString = new String[0];
        if (type == 3) {
            elementsInString = StringToArrayUtil.stringToArrayWithSlash(date);
        } else if (type == 7) {
            elementsInString = StringToArrayUtil.stringToArrayWithHyphen(date);
        }
        int day = 1;
        int month = 1;
        int year = 0;
        if (elementsInString.length == 0 && (date.matches("^\\/\\/$") || date.matches("^\\-\\-$"))) {
            return new int[]{year, month, day};
        }
        try {
            if (!elementsInString[0].equals("")) {
                for (int i = 0; i < 12; i++) {
                    if (elementsInString[0].equals(Date.MONTHS[i])) {
                        month = i + 1;
                        i = 12;
                    }
                }
            }
            if (!elementsInString[1].equals("")) {
                day = Integer.parseInt(elementsInString[1]);
            }
            if (!elementsInString[2].equals("")) {
                year = Integer.parseInt(elementsInString[2]);
            }
        } catch (Exception e) {
            return null;
        }

        if (isValidDate(day, month, year)) {
            return new int[]{year, month, day};
        } else {
            return null;
        }
    }

    private static int[] convertStringDateToArrayWithTimeType(String date, int type) {
        String[] elementsInString = new String[0];
        if (type == 4) {
            elementsInString = StringToArrayUtil.stringToArrayWithSlash(date);
        } else if (type == 8) {
            elementsInString = StringToArrayUtil.stringToArrayWithHyphen(date);
        }
        int day = 1;
        int month = 1;
        int year = 0;
        int hour;
        int minute;

        try {
            if (elementsInString.length == 0 && (date.matches("^\\/\\/") || date.matches("^\\-\\-"))) {
                return new int[]{year, month, day};
            } else {
                if (elementsInString[0].equals("")) {
                    day = 1;
                } else {
                    day = Integer.parseInt(elementsInString[0]);
                }
                if (elementsInString[1].equals("")) {
                    month = 1;
                } else {
                    for (int i = 0; i < 12; i++) {
                        if (elementsInString[1].equals(Date.MONTHS[i])) {
                            month = i + 1;
                            i = 12;
                        }
                    }
                }
                String[] yearAndTime = StringToArrayUtil.stringToArrayWithSpace(elementsInString[2]);
                if (yearAndTime[0].equals("")) {
                    year = 0;
                } else {
                    year = Integer.parseInt(yearAndTime[0]);
                }
                String[] timeElements = StringToArrayUtil.stringToArrayWithColon(yearAndTime[1]);
                if (timeElements[0].equals("")) {
                    hour = 0;
                } else {
                    hour = Integer.parseInt(timeElements[0]);
                    if (hour < 0 || hour >= 24) {
                        return null;
                    }
                }

                if (timeElements[1].equals("")) {
                    minute = 0;
                } else {
                    minute = Integer.parseInt(timeElements[1]);
                    if (minute < 0 || minute >= 60) {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        if (isValidDate(day, month, year)) {
            return new int[]{year, month, day, hour, minute};
        } else {
            return null;
        }
    }

    private static boolean isValidDate(int day, int month, int year) {
        boolean isYearValid = year >= 0;
        boolean isMonthValid = month > 0 && month < 12;
        boolean isDayValid = day > 0 && day < 32;

        if (isDayValid && isMonthValid && isYearValid) {
            if ((month == 2 && (isLeapYear(year) && day < 30) || (!isLeapYear(year) && day < 29))) {
                return true;
            } else if (((month == 4 || month == 6 || month == 9 || month == 11) && day < 31)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
