package ua.com.alevel.date;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TransformDates {

    private static List<String> dates = new ArrayList<>();
    private static final String PATH = "files/dateOutput.txt";

    public static void main(String[] args) {
        List<String> unverifiedDates = ReadFromFile.getDatesFromFile();

        for (String date : unverifiedDates) {
            String newDate = transformDate(date);
            if (newDate != null) {
                dates.add(newDate);
            }
        }
        clearFile();
        writeToFile();
    }

    private static String[] stringToArrayWithSlash(String string) {
        String newInput = string.replaceAll("/", "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }

    private static String[] stringToArrayWithHyphen(String string) {
        String newInput = string.replaceAll("-", "\n");
        Pattern pattern = Pattern.compile("\n");
        return pattern.split(newInput);
    }

    private static String transformDate(String date) {
        boolean isCorrectDateFormat = true;
        String validDate = null;
        String[] dateElements = stringToArrayWithHyphen(date);
        if (dateElements.length != 3) {
            isCorrectDateFormat = false;
            dateElements = stringToArrayWithSlash(date);
            if (dateElements.length == 3) {
                isCorrectDateFormat = true;
            }
        }

        if (isCorrectDateFormat) {
            int[] integerDate = stringToIntArray(dateElements);
            if (integerDate.length != 0) {
                validDate = intArrayToFinalString(integerDate);
            }
        }
        return validDate;
    }

    private static int[] stringToIntArray(String[] dateElements) {
        int[] integerDate = new int[3];
        for (int i = 0; i < 3; i++) {
            try {
                integerDate[i] = Integer.parseInt(dateElements[i]);
            } catch (Exception e) {
                return new int[0];
            }
        }
        return integerDate;
    }

    private static String intArrayToFinalString(int[] integerDate) {
        int numberOfLargeNumbers = 0;
        int day = 0;
        int month = 0;
        int year = 0;

        for (int dateElement : integerDate) {
            if (dateElement < 1) {
                return null;
            }
            if (dateElement > 31) {
                numberOfLargeNumbers++;
            }
        }

        if (numberOfLargeNumbers > 1) {
            return null;
        } else if (numberOfLargeNumbers == 1) {
            int[] dayAndMonth = new int[2];
            int indexDayAndMonth = 0;
            for (int i = 0; i < integerDate.length; i++) {
                if (integerDate[i] > 31) {
                    year = integerDate[i];
                } else {
                    dayAndMonth[indexDayAndMonth] = integerDate[i];
                    indexDayAndMonth++;
                }
            }

            if (dayAndMonth[0] < 12) {
                day = dayAndMonth[1];
                month = dayAndMonth[0];
            } else if (dayAndMonth[1] < 12) {
                day = dayAndMonth[0];
                month = dayAndMonth[1];
            } else {
                return null;
            }
        } else {
            if (integerDate[1] < 12) {
                day = integerDate[0];
                month = integerDate[1];
                year = integerDate[2];
            } else if (integerDate[0] < 12) {
                month = integerDate[0];
                day = integerDate[1];
                year = integerDate[2];
            } else if (integerDate[2] < 12) {
                year = integerDate[0];
                day = integerDate[1];
                month = integerDate[2];
            } else {
                return null;
            }
        }

        if (month == 2 && day > 29) {
            return null;
        } else if (month % 2 == 0 && month < 7 && day > 30) {
            return null;
        } else if (month % 2 != 0 && month > 7 && day > 30) {
            return null;
        }

        StringBuffer finalDate = new StringBuffer();
        finalDate.append(year);
        if (month < 10) {
            finalDate.append(0);
        }
        finalDate.append(month);
        if (day < 10) {
            finalDate.append(0);
        }
        finalDate.append(day);
        return finalDate.toString();
    }

    private static void clearFile() {
        try (FileWriter writer = new FileWriter(PATH)) {
            writer.write("");
        } catch (Exception ignored) {

        }
    }

    private static void writeToFile() {
        try (FileWriter writer = new FileWriter(PATH, true)) {
            for (String date : dates) {
                writer.write(date + "\n");
            }
        } catch (Exception ignored) {

        }
    }
}
