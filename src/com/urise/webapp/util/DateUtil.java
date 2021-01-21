package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(2021, 1, 21);
    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }
}
