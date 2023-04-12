package ru.netology;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public static String generateDate(int days, String pattern) {
        return LocalDate.now()
                .plusDays(days)
                .format(DateTimeFormatter
                        .ofPattern(pattern));
    }
}