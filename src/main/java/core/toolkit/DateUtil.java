package core.toolkit;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author trink
 */
public class DateUtil {

    private static SimpleDateFormat dateFormat   = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat uniqueFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static Date getDate() {
        return Calendar.getInstance().getTime();
    }

    public static Timestamp getTimestamp() {
        return Timestamp.valueOf(getDateString());
    }

    public static Timestamp getTimestamp(String date) {
        return Timestamp.valueOf(date);
    }

    public static synchronized String getDateSn() {
        return uniqueFormat.format(Calendar.getInstance().getTime()) + System.nanoTime();
    }

    public static synchronized String getDateString() {
        return dateFormat.format(getDate());
    }

    public static synchronized String getDateString(Date date) {
        return dateFormat.format(date);
    }
}
