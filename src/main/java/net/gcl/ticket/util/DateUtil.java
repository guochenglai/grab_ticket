package net.gcl.ticket.util;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by guochenglai on 2/13/17.
 */
public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DOT_DATE_PATTERN = "yyyy.MM.dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm:ss";
    public static final String FORMAT_NONE_SEPARATOR_PATTERN = "yyyyMMdd";

    public static String formatDateToString(Date date) {
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(DATE_PATTERN);
        String formattedTime = fastDateFormat.format(date);
        return formattedTime;
    }

    public static String formatDateToString(Date date, String pattern) {
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(pattern);
        String formattedTime = fastDateFormat.format(date);
        return formattedTime;
    }

    public static Date parseStringToDate(String dateString) {
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(DATE_PATTERN);
        Date parsedDate = null;
        try {
            parsedDate = fastDateFormat.parse(dateString);
        } catch (Exception e) {
            LOGGER.error("parse string to date cause exception ", e);
        }
        return parsedDate;
    }
    public static Date parseStringToDate(String dateString,String pattern) {
        FastDateFormat fastDateFormat = FastDateFormat.getInstance(pattern);
        Date parsedDate = null;
        try {
            parsedDate = fastDateFormat.parse(dateString);
        } catch (Exception e) {
            LOGGER.error("parse string to date cause exception ", e);
        }
        return parsedDate;
    }


}
