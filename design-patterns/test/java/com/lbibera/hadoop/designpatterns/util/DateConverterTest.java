package com.lbibera.hadoop.designpatterns.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.lbibera.hadoop.designpatterns.utils.DateConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DateConverterTest {

    private static final String DATE_STRING = "2010-04-04T08:48:51.347";
    private static final LocalDateTime DATE_JAVA = LocalDateTime.of(2010, 4, 4, 8, 48, 51, 347000000);

    @Test
    @DisplayName("String to Date")
    void shouldParseDateStringCorrectly() {
        String sampleDate = DATE_STRING;
        LocalDateTime expectedDate = DATE_JAVA;

        LocalDateTime actualDate = DateConverter.stringToDate(sampleDate);

        assertEquals(expectedDate.getYear(), actualDate.getYear());
        assertEquals(expectedDate.getMonth(), actualDate.getMonth());
        assertEquals(expectedDate.getDayOfMonth(), actualDate.getDayOfMonth());
        assertEquals(expectedDate.getHour(), actualDate.getHour());
        assertEquals(expectedDate.getMinute(), actualDate.getMinute());
        assertEquals(expectedDate.getSecond(), actualDate.getSecond());
        assertEquals(expectedDate.getNano(), actualDate.getNano());
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Date to String")
    void shouldConvertDateToString() {
        LocalDateTime sampleDate = DATE_JAVA;
        String expectedDate = DATE_STRING;

        String actualDate = DateConverter.dateToString(sampleDate);

        assertEquals(expectedDate, actualDate);
    }
}
