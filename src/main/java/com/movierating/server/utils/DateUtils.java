package com.movierating.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class serves to work with dates
 */
public class DateUtils {
    private static String formatDate = "yyyy-MM-dd";
    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static Date convertStringToDate(String dateStr) {
        Date date = null;
        SimpleDateFormat sFormat = new SimpleDateFormat(formatDate);
        try {
            date = sFormat.parse(dateStr);
        } catch (ParseException e) {
            log.warn("parse date exception + message = " + e.getMessage());
        }
        return date;
    }

    public static String dateToString(Date date) {

        DateFormat formatter = new SimpleDateFormat(formatDate);
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

    public static String getFormatDate() {
        return formatDate;
    }

    public static void setFormatDate(String formatDate) {
        DateUtils.formatDate = formatDate;
    }
}
