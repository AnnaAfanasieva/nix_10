package ua.com.alevel.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class ConvertStringTest {

    private ConvertString converter;

    @Test
    void testConvertStringToDate() throws ParseException {
        String currentData = "2001-04-24";
        Date result = ConvertString.convertStringToDate(currentData);
        assertEquals(24, result.getDate());
        assertEquals(3, result.getMonth());
        assertEquals(101, result.getYear());
    }
}
