package com.u3.dontdistraction.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by U3 on 2015/7/5.
 */
public class DateTools {
    public static String getDay(Date date) {
        return getFormatDateTime(date, "yyyy-MM-dd");
    }
    public static String getTime(Date date) {
        return getFormatDateTime(date, "HH:mm:ss");
    }
    public static String getFormatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
