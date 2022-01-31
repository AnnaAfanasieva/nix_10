package ua.com.alevel.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public final class ConvertString {

    private ConvertString() {

    }
    public static Date convertStringToDate(String stringDate) throws ParseException {
        Pattern pattern = Pattern.compile("-");
        String[] dateArray = pattern.split(stringDate);
        String dateString = dateArray[2] + "." + dateArray[1] + "." + dateArray[0];
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Date date= format.parse(dateString);
        date = DateUtils.addHours(date, 2);
        return date;
    }
}
