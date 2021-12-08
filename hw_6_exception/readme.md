## HW 6 (Dates)

### Task
Implement a console application with a user interface that will provide the user with the following capabilities:
- Finding the difference between dates in milliseconds, seconds, minutes, hours, days and years
- Adding milliseconds, seconds, minutes, hours, days and years to the date
- Subtraction of milliseconds, seconds, minutes, hours, days and years from the date
- Comparing the list of dates in descending and ascending order

****The countdown starts from January 1, 0****

Implement without using the Date, Calendar, DateTime classes and other implemented features, and also take into account the fact that there is a leap year in calculations
### Solution
To start the application, you need to run the startWitClean.sh script

Before entering a date, the user must select 1 of 8 date input and output formats:
1. dd/mm/yy (example: 24/04/21)
2. m/dd/yyyy (example: 4/24/2021)
3. mmm/dd/yy (example: April/24/21)
4. dd/mmm/yyyy 00:00 (example: 24/April/2021 11:45)
5. dd-mm-yy (example: 24-04-21)
6. m-dd-yyyy (example: 4-24-2021)
7. mmm-dd-yy (example: April-24-21)
8. dd-mmm-yyyy 00:00 (example: 24-April-2021 11:45)

To work with dates, a date module was created with the Date class, which implements the following methods for working with dates:
- Finding the difference between dates in milliseconds, seconds, minutes, hours, days and years
- Adding milliseconds, seconds, minutes, hours, days and years to the date
- Subtraction of milliseconds, seconds, minutes, hours, days and years from the date

The DateUtil.java class contains the following methods:
- Sorting the list of dates in ascending or descending order
- Check for compliance of the entered string with the selected input and output date type
- Converting a string to an object of type Date

The StringToArrayUtil class contains methods for splitting a string into an array of strings through various delimiters

The DateApplication class contains the main application logic