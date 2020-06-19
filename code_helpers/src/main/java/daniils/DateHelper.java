package daniils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateHelper {
    public static String dateFormat(String format, Calendar calendar) {
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public static Calendar changeCalendar(Calendar calendar, int field, int amount) {
        calendar.add(field, amount);
        return calendar;
    }

    public static Calendar copyCalendar(Calendar calendar) {
        return new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        );
    }

    public static Calendar copyCalendarWithChanges(Calendar calendar, int field, int amount) {
        var result = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        );
        result.add(field, amount);
        return result;
    }

}