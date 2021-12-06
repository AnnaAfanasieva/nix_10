package ua.com.alevel;

import ua.com.alevel.util.CalendarUtil;

import java.util.Scanner;

public class CalendarApplication {

    private static int INPUT_OUTPUT_TYPE = 4;

    public static void main(String[] args) {
        int chooseCreateNewDate;
        int chooseOption = 1;
        Date currentDate;
        Date dateToFindDifference;

        while (true) {
            showTopMenu();
            System.out.print("Введите ваш выбор: ");
            try (Scanner in = new Scanner(System.in)) {
                chooseCreateNewDate = in.nextInt();
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
                while (chooseOption != 0) {
                    showDateMenu();
                    System.out.print("Введите ваш выбор: ");
                    try (Scanner in = new Scanner(System.in)) {
                        chooseOption = in.nextInt();
                        if (chooseOption < 0 || chooseOption > 22) {
                            System.out.println("Из-за ошибок при вводе автоматически выбрана опция 1");
                            chooseOption = 1;
                        }
                    } catch (Exception e) {
                        System.out.println("Из-за ошибок при вводе автоматически выбрана опция 1");
                        chooseOption = 1;
                    }

                    switch (chooseOption) {
                        case 1:
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 2:
                            changeDateFormat();
                            break;
                        case 3:
                            dateToFindDifference = createNewDate();
                            System.out.print("Разница между датами = " + currentDate.differenceInMilliseconds(dateToFindDifference) + " миллисекунд");
                            break;
                        case 4:
                            dateToFindDifference = createNewDate();
                            System.out.print("Разница между датами = " + currentDate.differenceInSeconds(dateToFindDifference) + " секунд");
                            break;
                        case 5:
                            dateToFindDifference = createNewDate();
                            System.out.print("Разница между датами = " + currentDate.differenceInMinutes(dateToFindDifference) + " минут");
                            break;
                        case 6:
                            dateToFindDifference = createNewDate();
                            System.out.print("Разница между датами = " + currentDate.differenceInHours(dateToFindDifference) + " часов");
                            break;
                        case 7:
                            dateToFindDifference = createNewDate();
                            System.out.print("Разница между датами = " + currentDate.differenceInDays(dateToFindDifference) + " дней");
                            break;
                        case 8:
                            dateToFindDifference = createNewDate();
                            System.out.print("Разница между датами = " + currentDate.differenceInYears(dateToFindDifference) + " лет");
                            break;
                        case 9:
                            currentDate.addToDateMilliseconds(inputTime("миллисекунд"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 10:
                            currentDate.addToDateSeconds(inputTime("секунд"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 11:
                            currentDate.addToDateMinutes(inputTime("минут"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 12:
                            currentDate.addToDateHours(inputTime("часов"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 13:
                            currentDate.addToDateDays(inputTime("дней"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 14:
                            currentDate.addToDateYears(inputTime("годов"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 15:
                            currentDate.subtractMillisecondsFromDate(inputTime("миллисекунд"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 16:
                            currentDate.subtractSecondsFromDate(inputTime("секунд"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 17:
                            currentDate.subtractMinutesFromDate(inputTime("минут"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 18:
                            currentDate.subtractHoursFromDate(inputTime("часов"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 19:
                            currentDate.subtractDaysFromDate(inputTime("дней"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 20:
                            currentDate.subtractYearsFromDate(inputTime("годов"));
                            System.out.print("Измененная дата: ");
                            currentDate.showDate(INPUT_OUTPUT_TYPE);
                            break;
                        case 21:
                            //TODO сравнить перечень дат
                            break;
                    }
                }
            } else {
                System.out.println("Выход из приложения");
                System.exit(1);
            }
        }
    }

    private static void chooseDateFormat() {
        System.out.println("\nВведите дату в любом предложенном формате: \n");
        System.out.println("1 - dd/mm/yy\tпример: 01/12/21");
        System.out.println("2 - m/dd/yyyy\tпример: 3/4/2021");
        System.out.println("3 - mmm/dd/yy\tпример: March/4/21");
        System.out.println("4 - dd/mmm/yyyy 00:00\t\tпример: 09/Апрель/789 45:23");
        System.out.println();
        System.out.println("5 - dd-mm-yy\tпример: 01-12-21");
        System.out.println("6 - m-d-yyyy\tпример: 3-4-2021");
        System.out.println("7 - mmm-d-yy\tпример: March-4-21");
        System.out.println("8 - dd-mmm-yyyy 00:00\t\tпример: 09-Апрель-789 45:23\n");
    }

    private static void showTopMenu() {
        System.out.println("\nВыберите опцию: \n");
        System.out.println("1 - Ввести дату");
        System.out.println("0 - Выйти\n");
    }

    private static void showDateMenu() {
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
        System.out.println("0 - Вернуться на главное меню");
    }

    private static void changeDateFormat() {
        Scanner in = new Scanner(System.in);
        chooseDateFormat();
        System.out.print("Введите ваш выбор: ");
        try {
            INPUT_OUTPUT_TYPE = in.nextInt();
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
        Scanner in = new Scanner(System.in);
        Date currentDate = null;
        while (currentDate == null) {
            System.out.print("Введите новую дату: ");
            String newDateInString = in.nextLine();
            currentDate = CalendarUtil.createDateFromString(newDateInString);
            if (currentDate == null) {
                System.out.println("Ошибка при вводе. Повторите попытку");
            }
        }
        return currentDate;
    }

    private static long inputTime(String timeType) {
        System.out.print("Введите количество " + timeType + ": ");
        long time = 0;
        try (Scanner in = new Scanner(System.in)) {
            time = in.nextInt();
        } catch (Exception e) {
            System.out.println("Ошибка при вводе");
        }
        return time;
    }
}
