package com.hadooppractice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * http://stackoverflow.com/questions/22463062/how-to-parse-format-dates-with-localdatetime-java-8
 */
public class DateConverter {

//    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
            .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true).toFormatter();

    public static LocalDateTime stringToDate(String dateString) {
        return LocalDateTime.parse(dateString, FORMATTER);
    }

    public static String dateToString(LocalDateTime date) {
        return date.format(FORMATTER);
    }
}
