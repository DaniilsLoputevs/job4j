package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHelper {
    public static String dateFormat(String format, Calendar calendar) {
        return new SimpleDateFormat(format).format(calendar.getTime());
    }
}
