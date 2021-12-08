package ua.com.alevel;

import ua.com.alevel.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DateApplication {

    private static int INPUT_OUTPUT_TYPE = 4;

    public static void main(String[] args) {
        int chooseCreateNewDate;
        int chooseOption = 1;
        Date currentDate;
        Date dateToFindDifference;

        while (true) {
            showTopMenu();
            System.out.print("Введите ваш выбор: ");
            Scanner in = new Scanner(System.in);
            String chooseCreateNewDateString = in.nextLine();
            try {
                chooseCreateNewDate = Integer.parseInt(chooseCreateNewDateString);
                if (chooseCreateNewDate != 1 && chooseCreateNewDate != 0) {
                    System.out.println("Из-за ошибок при вводе автоматически выбрана опция 1");
                    chooseCreateNewDate = 1;
                }
            } catch (Exception e) {
                System.out.println("Из-за ошибок при вводе автоматически выбрана опция 1");
                chooseCreateNewDate = 1;
            }

            if (chooseCreateNewDate == 1) {
                changeDateFormat();
                currentDate = createNewDate();
                chooseOption = 1;
                while (chooseOption != 0) {
                    showDateMenu();
                    System.out.print("Введите ваш выбор: ");
                    try {
                        chooseOption = in.nextInt();
                        if (chooseOption < 0 || chooseOption > 23) {
                            System.out.println("Из-за ошибок при вводе автоматически выбрана опция 1");
                            chooseOption = 1;
                        }
                    } catch (Exception e) {
                        System.out.println("Из-за ошибок при вводе автоматически выбрана опция 1");
                        chooseOption = 1;
                    }

                    switch (chooseOption) {
                        case 1:
                            System.out.println("Дата = " + currentDate.showDate(INPUT_OUTPUT_TYPE));
                            break;
                        case 2:
                            changeDateFormat();
                            break;
                        case 3:
                            dateToFindDifference = createNewDate();
                            System.out.println("Разница между датами = " + currentDate.differenceInMilliseconds(dateToFindDifference) + " миллисекунд");
                            break;
                        case 4:
                            dateToFindDifference = createNewDate();
                            System.out.println("Разница между датами = " + currentDate.differenceInSeconds(dateToFindDifference) + " секунд");
                            break;
                        case 5:
                            dateToFindDifference = createNewDate();
                            System.out.println("Разница между датами = " + currentDate.differenceInMinutes(dateToFindDifference) + " минут");
                            break;
                        case 6:
                            dateToFindDifference = createNewDate();
                            System.out.println("Разница между датами = " + currentDate.differenceInHours(dateToFindDifference) + " часов");
                            break;
                        case 7:
                            dateToFindDifference = createNewDate();
                            System.out.println("Разница между датами = " + currentDate.differenceInDays(dateToFindDifference) + " дней");
                            break;
                        case 8:
                            dateToFindDifference = createNewDate();
                            System.out.println("Разница между датами = " + currentDate.differenceInYears(dateToFindDifference) + " лет");
                            break;
                        case 9:
                            currentDate.addToDateMilliseconds(inputTime("миллисекунд"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 10:
                            currentDate.addToDateSeconds(inputTime("секунд"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 11:
                            currentDate.addToDateMinutes(inputTime("минут"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 12:
                            currentDate.addToDateHours(inputTime("часов"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 13:
                            currentDate.addToDateDays(inputTime("дней"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 14:
                            currentDate.addToDateYears(inputTime("годов"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 15:
                            currentDate.subtractMillisecondsFromDate(inputTime("миллисекунд"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 16:
                            currentDate.subtractSecondsFromDate(inputTime("секунд"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 17:
                            currentDate.subtractMinutesFromDate(inputTime("минут"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 18:
                            currentDate.subtractHoursFromDate(inputTime("часов"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 19:
                            currentDate.subtractDaysFromDate(inputTime("дней"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 20:
                            currentDate.subtractYearsFromDate(inputTime("годов"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            System.out.println();
                            break;
                        case 21:
                            DateUtil.sortDates(inputDateToSort(), INPUT_OUTPUT_TYPE, chooseSortingIsAsc());
                            break;
                        case 22:
                            System.out.println(currentDate.showAllDate());
                    }
                }
            } else {
                System.out.println("Выход из приложения");
                System.exit(1);
            }
        }
    }

    private static void chooseDateFormatMenu() {
        System.out.println("\nВведите дату в любом предложенном формате: \n");
        System.out.println("1 - dd/mm/yy\tпример: 01/12/21");
        System.out.println("2 - m/dd/yyyy\tпример: 3/4/2021");
        System.out.println("3 - mmm/dd/yy\tпример: March/4/21");
        System.out.println("4 - dd/mmm/yyyy 00:00\t\tпример: 09/April/789 45:23");
        System.out.println();
        System.out.println("5 - dd-mm-yy\tпример: 01-12-21");
        System.out.println("6 - m-d-yyyy\tпример: 3-4-2021");
        System.out.println("7 - mmm-d-yy\tпример: March-4-21");
        System.out.println("8 - dd-mmm-yyyy 00:00\t\tпример: 09-April-789 45:23\n");
    }

    private static void showTopMenu() {
        System.out.println("\nВыберите опцию: \n");
        System.out.println("1 - Ввести дату");
        System.out.println("0 - Выйти\n");
    }

    private static void showDateMenu() {
        System.out.println("Выберите опцию: \n");
        System.out.println("1 - Показать текущую дату");
        System.out.println("2 - Изменить формат даты");
        System.out.println();
        System.out.println("3 - Найти разницу между датами в миллисекундах");
        System.out.println("4 - Найти разницу между датами в секундах");
        System.out.println("5 - Найти разницу между датами в минутах");
        System.out.println("6 - Найти разницу между датами в часах");
        System.out.println("7 - Найти разницу между датами в днях");
        System.out.println("8 - Найти разницу между датами в годах");
        System.out.println();
        System.out.println("9 - Прибавить к дате миллисекунды");
        System.out.println("10 - Прибавить к дате секунды");
        System.out.println("11 - Прибавить к дате минуты");
        System.out.println("12 - Прибавить к дате часы");
        System.out.println("13 - Прибавить к дате дни");
        System.out.println("14 - Прибавить к дате годы");
        System.out.println();
        System.out.println("15 - Вычесть из даты миллисекунды");
        System.out.println("16 - Вычесть из даты секунды");
        System.out.println("17 - Вычесть из даты минуты");
        System.out.println("18 - Вычесть из даты часы");
        System.out.println("19 - Вычесть из даты дни");
        System.out.println("20 - Вычесть из даты годы");
        System.out.println();
        System.out.println("21 - Сравнить перечень дат");
        System.out.println("22 - Посмотреть полную дату");
        System.out.println("0 - Вернуться на главное меню");
    }

    private static void changeDateFormat() {
        chooseDateFormatMenu();
        System.out.print("Введите ваш выбор: ");
        Scanner in = new Scanner(System.in);
        String typeString = in.nextLine();
        try {
            INPUT_OUTPUT_TYPE = Integer.parseInt(typeString);
            if (INPUT_OUTPUT_TYPE < 1 || INPUT_OUTPUT_TYPE > 8) {
                System.out.println("Из-за ошибок при вводе автоматически выбран формат 4");
                INPUT_OUTPUT_TYPE = 4;
            }
        } catch (Exception e) {
            System.out.println("Из-за ошибок при вводе автоматически выбран формат 4");
            INPUT_OUTPUT_TYPE = 4;
        }
    }

    private static Date createNewDate() {
        Date currentDate = null;
        Scanner in = new Scanner(System.in);
        while (currentDate == null) {
            System.out.print("Введите новую дату (год введите полностью, независимо от выбранного формата): ");
            try {
                String newDateInString = in.nextLine();
                currentDate = DateUtil.createDateFromString(newDateInString, INPUT_OUTPUT_TYPE);
            } catch (Exception ignored) {

            }

            if (currentDate == null) {
                System.out.println("Ошибка при вводе. Повторите попытку");
            }
        }
        return currentDate;
    }

    private static long inputTime(String timeType) {
        System.out.print("Введите количество " + timeType + ": ");
        long time = 0;
        Scanner in = new Scanner(System.in);
        String timeString = in.nextLine();
        try {
            time = Integer.parseInt(timeString);
        } catch (Exception e) {
            System.out.println("Ошибка при вводе");
        }
        return time;
    }

    private static boolean chooseSortingIsAsc() {
        int chooseSorting;
        System.out.println("\nВыберите сортировку:\n1 - В порядке возрастания\n2 - в порядке убывания");
        System.out.print("Введите ваш выбор: ");
        Scanner in = new Scanner(System.in);
        String chooseSortingString = in.nextLine();
        try {
            chooseSorting = Integer.parseInt(chooseSortingString);
            if (chooseSorting == 1) {
                return true;
            } else if (chooseSorting == 2) {
                return false;
            } else {
                System.out.println("Из-за ошибок при вводе автоматически выбрана сортировка по возростанию");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Из-за ошибок при вводе автоматически выбрана сортировка по возростанию");
            return true;
        }
    }

    private static List<Date> inputDateToSort() {
        List<Date> dates = new ArrayList<>();
        int chooseInInputDateToSort;
        Date newDateToSort;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("\nВыберите опцию:\n1 - Ввести дату\n0 - Закончить ввод");
            System.out.print("Введите ваш выбор: ");
            String chooseInInputDateToSortString = in.nextLine();
            try {
                chooseInInputDateToSort = Integer.parseInt(chooseInInputDateToSortString);
                if (chooseInInputDateToSort == 1) {
                    newDateToSort = createNewDate();
                    dates.add(newDateToSort);
                } else if (chooseInInputDateToSort == 0) {
                    return dates;
                } else {
                    System.out.println("Ошибка при вводе");
                }
            } catch (Exception e) {
                System.out.println("Ошибка при вводе");
            }
        }
    }
}
