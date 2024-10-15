package pair.task.module;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Parer for data string representation.
 * To add new supported format modify FORMATS list.
 * NULL and empty date are handled us today.
 */
public class DateParser {

    private static final String[] FORMATS = {
            "yyyy-MM-dd",
            "MM/dd/yy",
            "yyMMdd",
            "dd MMM yyyy",
            "MM/DD/YY",
            "DD/MM/YY",
            "YY/MM/DD",
            "M/D/YY",
            "D/M/YY",
            "YY/M/D",
            "MMDDYY",
            "DDMMYY",
            "YYMMDD",
            "day/YY",
            "YY/day"
    };

    public static Date getDate(String dateString) {
        if(dateString == null || "NULL".equalsIgnoreCase(dateString.trim()) || dateString.isBlank()) {
            return new Date();
        }
        dateString = dateString.trim();
        Date date = null;
        for(String format : FORMATS) {
            date = parseDate(dateString, format);
            if(date != null) {
                break;
            }
        }
        return date;
    }

    private static Date parseDate(String dateString, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
