package com.support.utils;

import com.support.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;


/**
 * 文件名称： 日期工具类
 **/
@SuppressWarnings("unused")
public class DateUtil {

    //日期格式
    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String Y_M_D_HM = "yyyy-MM-dd HH:mm";
    public static final String Y_M_D_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String Y_M_D_HMSS = "yyyy-MM-dd HH:mm:ss:SSS";
    public static final String YMD = "yyyyMMdd";
    public static final String YMDHM = "yyyyMMddHHmm";
    public static final String YMDHMS = "yyyyMMddHHmmss";
    public static final String ymd = "yyyy/MM/dd";
    public static final String ymd_HM = "yyyy/MM/dd HH:mm";
    public static final String ymd_HMS = "yyyy/MM/dd HH:mm:ss";
    public static final String YMDHMSS = "yyyyMMddHHmmssSSS";

    /**
     * 将Date类型转换为字符串
     */
    public static String format(Date date) {
        return format(date, Y_M_D_HMS);
    }

    /**
     * 将Date类型转换为指定格式字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isEmpty(pattern) || StringUtils.isBlank(pattern) || "null".equals(pattern)) {
            pattern = Y_M_D_HMS;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式字符串转换成日期
     */
    public static Date format(String dateString) {
        try {
            return format(dateString, null);
        } catch (Exception e) {
            return format(dateString, Y_M_D);
        }
    }

    /**
     * 将字符串转换为Date类型
     */
    public static Date format(String dateString, String pattern) {
        if (StringUtils.isEmpty(pattern) || StringUtils.isBlank(pattern) || "null".equals(pattern)) {
            pattern = Y_M_D_HMS;
        }
        if (StringUtils.isEmpty(dateString) || StringUtils.isBlank(dateString) || "null".equals(dateString)) {
            return null;
        }
        Date d;
        try {
            d = new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException pe) {
            throw new RuntimeException("日期转换异常：" + dateString);
        }
        return d;
    }

    /**
     * 获取当前时间，格式:yyyyMMddHHmmss
     */
    public static String getCurrDateTime() {
        return DateFormatUtils.format(System.currentTimeMillis(), YMDHMS);
    }

    /**
     * 获取当前日期，格式:yyyy-MM-dd
     */
    public static String getCurrDate() {
        return DateFormatUtils.format(System.currentTimeMillis(), Y_M_D);
    }

    /**
     * 智能将日期转成字符串
     */
    public static String smartFormat(Date date) {
        String dateStr;
        if (date == null) {
            dateStr = "";
        } else {
            try {
                dateStr = format(date, Y_M_D_HMS);
                //时分秒  
                if (dateStr.endsWith(" 00:00:00")) {
                    dateStr = dateStr.substring(0, 10);
                }
                //时分  
                else if (dateStr.endsWith("00:00")) {
                    dateStr = dateStr.substring(0, 16);
                }
                //秒  
                else if (dateStr.endsWith(":00")) {
                    dateStr = dateStr.substring(0, 16);
                }
            } catch (Exception ex) {
                throw new IllegalArgumentException("转换日期失败: " + ex.getMessage(), ex);
            }
        }
        return dateStr;
    }

    /**
     * 智能将字符串转成日期
     */
    public static Date smartFormat(String text) {
        Date date;
        try {
            if (text == null || text.length() == 0) {
                date = null;
            } else if (text.length() == 10) {
                date = format(text, Y_M_D);
            } else if (text.length() == 13) {
                date = new Date(Long.parseLong(text));
            } else if (text.length() == 16) {
                date = format(text, Y_M_D_HM);
            } else if (text.length() == 19) {
                date = format(text, Y_M_D_HMS);
            } else {
                throw new IllegalArgumentException("日期长度不符合要求!");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("日期转换失败!");
        }
        return date;
    }

    /**
     * 获取当前时间，格式:yyyyMMddHHmmssSSS
     */
    public static String getCurrDateTimeMillis() {
        return DateFormatUtils.format(System.currentTimeMillis(), YMDHMSS);
    }

    /**
     * 当前日期的开始时间
     */
    public static Date formatBegin(Object obj) {
        return getDate(format(obj), 0, 0, 1, 0, 0, 0);
    }

    /**
     * 当天日期的结束时间
     */
    public static Date formatEnd(Object obj) {
        return getDate(format(obj), 0, 12, -1, 23, 59, 59);
    }

    public static Date getDate(String date, int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        String time = null;
        if (date.indexOf(' ') > 0) {
            String[] d = StringUtils.split(date, ' ');
            date = d[0];
            if (d.length >= 2) time = d[1];
        } else if (date.indexOf(':') > 0) {
            time = date;
            date = null;
        }
        if (date != null) {
            String[] d = StringUtils.split(date, '-');
            try {
                if (d.length == 1) {
                    year = Integer.parseInt(d[0]);
                    if (second == 59) {
                        second++;
                        month--;
                    }
                } else if (d.length == 2) {
                    year = Integer.parseInt(d[0]);
                    month = Integer.parseInt(d[1]) - 1;
                    if (month > 11) {
                        day = month;
                        month = year;
                        year = 0;
                    }
                } else if (d.length == 3) {
                    year = Integer.parseInt(d[0]);
                    month = Integer.parseInt(d[1]) - 1;
                    day = Integer.parseInt(d[2]);
                }
            } catch (Exception e) {
                throw new SystemException("无效的日期格式!");
            }
            if (year > 0) {
                calendar.set(Calendar.YEAR, year);
            }
            if (day == -1) {
                month++;
                day++;
            }
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
        }
        if (time != null) {
            String sTime = null;
            if (time.indexOf('.') > 0) {
                String[] d = StringUtils.split(time, '.');
                time = d[0];
                sTime = d[1];
            }
            if (time.indexOf(':') > 0) {
                String[] d = StringUtils.split(time, ':');
                try {
                    hour = Integer.parseInt(d[0]);
                    if (d.length >= 2) minute = Integer.parseInt(d[1]);
                    if (d.length >= 3 && StringUtils.isNotEmpty(d[2])) second = Integer.parseInt(d[2]);
                } catch (Exception e) {
                    throw new SystemException("无效的日期格式!");
                }
            } else if (Pattern.matches("\\d+", time)) {
                hour = Integer.parseInt(time);
                if (hour > 23) hour = 0;
            }
            if (sTime != null && Pattern.matches("\\d+", sTime)) {
                calendar.set(Calendar.MILLISECOND, Integer.parseInt(sTime));
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 将日期格式化为yyyy-MM-dd格式  参数为日期对象或者字符串日期都可以
     */
    public static String format(Object obj) {
        String value;
        if (obj instanceof Date) {
            value = format((Date) obj, Y_M_D);
        } else if (obj instanceof Long) {
            value = format(new Date((Long) obj), Y_M_D);
        } else {
            value = obj.toString();
        }
        return value;
    }

    /**
     * 计算两个日期相差的天数
     */
    public static int diffDays(Date beginDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            beginDate = sdf.parse(sdf.format(beginDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = sdf.parse(sdf.format(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        long between_days = Math.abs(time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

}
