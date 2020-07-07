package com.hbzf.draw.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 */
@Slf4j
public class DateUtil {

    private static final String MILLI_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String MILLI_TIME_SIMPLE_FORMAT = "yyyyMMddHHmmssSSS";

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String END_TIME = " 23:59:59";

    public static Date plusMonth(Date date, int month) {
        return new DateTime(date).plusMonths(month).toDate();
    }

    public static Date plusDay(Date date, int day) {
        return new DateTime(date).plusDays(day).toDate();
    }

    public static Date plusHour(Date date, int hour) {
        return new DateTime(date).plusHours(hour).toDate();
    }


    public static Date plusMinutes(Date date, int minutes) {
        return new DateTime(date).plusMinutes(minutes).toDate();
	}

    public static Date formatStr(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        return DateTime.parse(time, DateTimeFormat.forPattern(TIME_FORMAT)).toDate();
    }

    public static String formatDateStr(Date date){
        if(date == null){
            return "";
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(TIME_FORMAT);
    }


    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date getDayBeginTime(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定时间精确到毫秒格式后的字符串
     * @param date
     * @return
     */
    public static String getMilliTimeFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(MILLI_TIME_FORMAT);
        return sdf.format(date);
    }

    public static String formatDateTime(Date time) {
        return new DateTime(time).toString(DateTimeFormat.forPattern(TIME_FORMAT));
    }

    public static String formatDate(Date time) {
        return new DateTime(time).toString(DateTimeFormat.forPattern(DATE_FORMAT));
    }

    public static Date formatDateStr(String time) {
        if(StringUtils.isEmpty(time)){
            return null;
        }

        try{
            return DateUtils.parseDate(time, new String[]{DATE_FORMAT});
        }catch (Exception e){
            log.error("string format date is error", e);
        }
       return null;
    }

    public static String getMilliSimpleTimeFormat(Date time) {
        return new DateTime(time).toString(DateTimeFormat.forPattern(MILLI_TIME_SIMPLE_FORMAT));
    }


}
