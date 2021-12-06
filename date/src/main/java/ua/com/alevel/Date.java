package ua.com.alevel;

public class Date {

    private long milliseconds;

    public static final long SECOND = 1_000;
    public static final long MINUTE = 60_000;
    public static final long HOUR = 3_600_000;
    public static final long DAY = 86_400_000;
    public static final long DAYS_28 = 2_419_200_000L;
    public static final long DAYS_29 = 2_505_600_000L;
    public static final long DAYS_30 = 2_592_000_000L;
    public static final long DAYS_31 = 2_678_400_000L;
    private static final String[] MONTHS = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static final long NOT_LEAP_YEARS = 31_536_000_000L;
    public static final long FOUR_YEARS = 126_230_400_000L;
//    public static final long LEAP_YEAR = 31_622_400_000L;

    public Date(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public String showDate(int type) {
        long milliseconds = this.milliseconds;
        int minutes = 0;
        int hours = 0;
        int day = 0;
        int month = 0;
        int year = 0;

        while ((milliseconds - FOUR_YEARS) >= 0) {
            year += 4;
            milliseconds -= FOUR_YEARS;
        }
        while ((milliseconds - NOT_LEAP_YEARS) >= 0) {
            year += 1;
            milliseconds -= NOT_LEAP_YEARS;
        }

        int monthNumber = 0;
        while ((milliseconds - DAYS_28) >= 0 || (milliseconds - DAYS_29) >= 0 || (milliseconds - DAYS_30) >= 0 || (milliseconds - DAYS_31) >= 0) {
            switch (monthNumber + 1) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    month += 1;
                    milliseconds -= DAYS_31;
                    break;
                case 2:
                    if (isLeapYear(year)) {
                        month += 1;
                        milliseconds -= DAYS_29;
                    } else {
                        month += 1;
                        milliseconds -= DAYS_28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    month += 1;
                    milliseconds -= DAYS_30;
                    break;
            }
            monthNumber++;
        }

        while ((milliseconds - DAY) >= 0) {
            day++;
            milliseconds -= DAY;
        }

        while ((milliseconds - HOUR) >= 0) {
            hours++;
            milliseconds -= HOUR;
        }

        while ((milliseconds - MINUTE) >= 0) {
            minutes++;
            milliseconds -= MINUTE;
        }

        if (type == 1) {
            return showDateFirstType(year, month, day, "/");
        } else if (type == 2) {
            return showDateSecondType(year, month, day, "/");
        } else if (type == 3) {
            return showDateThirdType(year, month, day, "/");
        } else if (type == 4) {
            return showDateWithTime(year, month, day, hours, minutes, "/");
        } else if (type == 5) {
            return showDateFirstType(year, month, day, "-");
        } else if (type == 6) {
            return showDateSecondType(year, month, day, "-");
        } else if (type == 7) {
            return showDateThirdType(year, month, day, "-");
        } else if (type == 8) {
            return showDateWithTime(year, month, day, hours, minutes, "-");
        }
        return null;
    }

    private String showDateFirstType(int year, int month, int day, String separator) {
        StringBuilder date = new StringBuilder();
        if (day <= 9) {
            date.append(0);
        }
        date.append(day).append(separator);
        if (month <= 9) {
            date.append(0);
        }
        date.append(month).append(separator);
        int yearFromTwoSymbols = year % 100;
        if (yearFromTwoSymbols <= 9) {
            date.append(0);
        }
        date.append(yearFromTwoSymbols);
        return "Текущая дата: " + date;
    }

    private String showDateSecondType(int year, int month, int day, String separator) {
        return "Текущая дата: " + month + separator + day + separator + year;
    }

    private String showDateThirdType(int year, int month, int day, String separator) {
        StringBuilder date = new StringBuilder();
        date.append(MONTHS[month - 1]).append(separator).append(day).append(separator);
        int yearFromTwoSymbols = year % 100;
        if (yearFromTwoSymbols <= 9) {
            date.append(0);
        }
        date.append(yearFromTwoSymbols);
        return "Текущая дата: " + date;
    }

    private String showDateWithTime(int year, int month, int day, int hours, int minutes, String separator) {
        StringBuilder date = new StringBuilder();
        if (day <= 9) {
            date.append(0);
        }
        date.append(day).append(separator).append(MONTHS[month - 1]).append(separator).append(year).append(" ");
        if (hours <= 9) {
            date.append(0);
        }
        date.append(hours).append(":");
        if (minutes <= 9) {
            date.append(0);
        }
        date.append(minutes);
        return "Текущая дата: " + date;
    }

    public long differenceInMilliseconds(Date secondDate) {
        return Math.abs(this.getMilliseconds() - secondDate.getMilliseconds());
    }

    public long differenceInSeconds(Date secondDate) {
        return differenceInMilliseconds(secondDate) / SECOND;
    }

    public long differenceInMinutes(Date secondDate) {
        return differenceInMilliseconds(secondDate) / MINUTE;
    }

    public long differenceInHours(Date secondDate) {
        return differenceInMilliseconds(secondDate) / HOUR;
    }

    public long differenceInDays(Date secondDate) {
        return differenceInMilliseconds(secondDate) / DAY;
    }

    //TODO разница между датами в годах
    public long differenceInYears(Date secondDate) {
        return 0;
    }

    public void addToDateMilliseconds(long milliseconds) {
        this.milliseconds += milliseconds;
    }

    public void addToDateSeconds(long seconds) {
        this.milliseconds += seconds * SECOND;
    }

    public void addToDateMinutes(long minutes) {
        this.milliseconds += minutes * MINUTE;
    }

    public void addToDateHours(long hours) {
        this.milliseconds += hours * HOUR;
    }

    public void addToDateDays(long days) {
        this.milliseconds += days * DAY;
    }

    //TODO прибавить к дате года
    public void addToDateYears(long years) {

    }

    public void subtractMillisecondsFromDate(long milliseconds) {
        long differenceBetweenDatesInMilliseconds = this.milliseconds - milliseconds;
        if (differenceBetweenDatesInMilliseconds > 0) {
            this.milliseconds = differenceBetweenDatesInMilliseconds;
        } else {
            System.out.println("Дата, которую вы хотите вычесть, больше начальной даты. Операция не была произведена");
        }
    }

    public void subtractSecondsFromDate(long seconds) {
        long differenceBetweenDatesInMilliseconds = this.milliseconds - (seconds * SECOND);
        if (differenceBetweenDatesInMilliseconds > 0) {
            this.milliseconds = differenceBetweenDatesInMilliseconds;
        } else {
            System.out.println("Дата, которую вы хотите вычесть, больше начальной даты. Операция не была произведена");
        }
    }

    public void subtractMinutesFromDate(long minutes) {
        long differenceBetweenDatesInMilliseconds = this.milliseconds - (minutes * MINUTE);
        if (differenceBetweenDatesInMilliseconds > 0) {
            this.milliseconds = differenceBetweenDatesInMilliseconds;
        } else {
            System.out.println("Дата, которую вы хотите вычесть, больше начальной даты. Операция не была произведена");
        }
    }

    public void subtractHoursFromDate(long hours) {
        long differenceBetweenDatesInMilliseconds = this.milliseconds - (hours * HOUR);
        if (differenceBetweenDatesInMilliseconds > 0) {
            this.milliseconds = differenceBetweenDatesInMilliseconds;
        } else {
            System.out.println("Дата, которую вы хотите вычесть, больше начальной даты. Операция не была произведена");
        }
    }

    public void subtractDaysFromDate(long days) {
        long differenceBetweenDatesInMilliseconds = this.milliseconds - (days * DAY);
        if (differenceBetweenDatesInMilliseconds > 0) {
            this.milliseconds = differenceBetweenDatesInMilliseconds;
        } else {
            System.out.println("Дата, которую вы хотите вычесть, больше начальной даты. Операция не была произведена");
        }
    }

    //TODO вычесть из даты года
    public void subtractYearsFromDate(long years) {

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
}
