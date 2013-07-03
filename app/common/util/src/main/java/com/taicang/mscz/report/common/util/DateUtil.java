/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.taicang.mscz.report.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

/**
 * 日期处理工具类.<br>
 * <p>
 * 此类改自iwallet-biz-common
 * </p>
 * @author xinjian.chen
 * @version $Id: DateUtil.java, v 0.1 2011-3-10 上午01:04:36 xinjian.chen Exp $
 */
public class DateUtil {

    private static final Logger log                  = LoggerFactory.getLogger(DateUtil.class);

    /** 一天代表的秒数 */
    public static final long    ONE_DAY_SECONDS      = 86400;

    /** 短日期格式 */
    public static final String  shortFormat          = "yyyyMMdd";

    /** 长日期格式 */
    public static final String  longFormat           = "yyyyMMddHHmmss";

    /** WEB页面日期格式 */
    public static final String  webFormat            = "yyyy-MM-dd";

    /** 时分秒日期格式 */
    public static final String  timeFormat           = "HHmmss";

    /** 年月日期格式 */
    public static final String  monthFormat          = "yyyyMM";

    /** 年月日中文日期格式 */
    public static final String  chineseDtFormat      = "yyyy年MM月dd日";

    /** 日期精确格式：精确到秒 */
    public static final String  newFormat            = "yyyy-MM-dd HH:mm:ss";

    /** 一天代表的毫秒数 */
    public static final long    ONE_DAY_MILL_SECONDS = 86400000;

    /**
     * 得到特定格式的DateFormat对象
     * @param pattern
     * @return
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * 日期特定格式转型
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 去掉日期的时分秒值
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 以特定格式将字符串转Date类型
     * @param sDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 将某日期转换为短日期格式的Date类型
     * @param sDate
     * @param delimit
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTimeWithDelimit(String sDate, String delimit)
                                                                               throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * 将"yyyyMMddHHmmss"形式的日期字符串转换为Date类型
     * @param sDate
     * @return
     */
    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    /**
     * 在当前日期加月份数,再加天数
     * @param date
     * @param months
     * @param days
     * @return
     */
    public static Date addMonthsAndDays(Date date, int months, int days) {
        Calendar sysDate = new GregorianCalendar();
        sysDate.setTime(date);
        sysDate.add(Calendar.MONTH, months);
        sysDate.add(Calendar.DAY_OF_MONTH, days);
        return sysDate.getTime();
    }

    /**
     * 将"yyyy-MM-dd HH:mm:ss"形式的日期字符串转换为Date类型
     * @param sDate
     * @return
     */
    public static Date parseDateNewFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        Date d = null;
        if ((sDate != null) && (sDate.length() == newFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 对WEB格式日期字符串转Date类型
     * @param sDate
     * @return
     */
    public static Date parseDateWebFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(webFormat);
        Date d = null;
        if ((sDate != null) && (sDate.length() == webFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * 计算当前时间几小时之后的时间
     *
     * @param date
     * @param hours
     *
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     *
     * @param date
     * @param minutes
     *
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * 对日期进行加N秒处理，返回Date类型
     * @param date1
     * @param secs
     *
     * @return
     */
    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * 判断输入的字符串是否为合法的小时
     *
     * @param hourStr
     *
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtil.isEmpty(hourStr) && StringUtil.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断输入的字符串是否为合法的分或秒
     *
     * @param minuteStr
     *
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtil.isEmpty(str) && StringUtil.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 取得新的日期
     *
     * @param date1 日期
     * @param days 天数
     *
     * @return 新的日期
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    /**
     * 对当前日期进行加N年处理
     * @param date
     * @param years
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addYears(Date date, int years) {
        date.setYear(date.getYear() + years);
        return date;
    }

    /**
     * 对当前日期进行加N年少一天处理
     * @param date
     * @param years
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addYearsWithOneDayAhead(Date date, int years) {
        date.setYear(date.getYear() + years);
        date.setDate(date.getDate() - 1);
        return date;
    }

    /**
     * 对日期进行加N月多一天处理
     * @param date
     * @param months
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addMonthsWithOneDayAhead(Date date, String months) {
        //规则，比如：1年期的合同，结束日期为起始日期加12个月减1天
        Date endDate = addMonthsAndDays(date, Integer.valueOf(months).intValue(), -1);

        //如果合同有效起始时间为2月29日，因为闰年关系，特殊对待
        if (date.getDate() == 29 && date.getMonth() == 1) {
            Date endDateAdd1day = addDays(endDate, 1);
            if (!(endDateAdd1day.getMonth() == 1 && endDateAdd1day.getDate() == 29)) {
                endDate = addDays(endDate, 1);
            }
        }
        return endDate;
    }

    /**
     * 对当前日期进行加N年少一天处理
     * @param date
     * @param years
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addYearsWithOneDayAheadWithoutSideEffect(Date date, int years) {
        Date returnDate = new Date();
        returnDate.setYear(date.getYear() + years);
        returnDate.setMonth(date.getMonth());
        returnDate.setDate(date.getDate() - 1);
        return returnDate;
    }

    /**
     * 以String格式得到某日期之后一天的日期
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static String getTomorrowDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * 对日期进行格式化并输出"yyyyMMddHHmmss"格式字符串
     * @param date
     * @return
     */
    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 对日期进行格式化，并输出yyyy-MM-dd HH:mm:ss形式的字符串
     * @param date
     * @return
     */
    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * 以特定格式对某日期进行格式化，并以String形式返回
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    /**
     * 获得某日期前一天对应日期
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static String getYesterDayDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * @return 当天的时间格式化为"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    /**
     * 获取某日期的WEB网页所需格式
     * @param date
     * @return
     */
    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得“X年X月X日”的日期格式
     *
     * @param date
     *
     * @return
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 返回今天当天的短日期格式字符串
     * @return
     */
    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * 返回特定日期当天时间以时分秒表示
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 计算当前时间之前某天日期，并以字符串形式返回
     * @param days
     * @return
     */
    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one 日期1
     * @param two 日期2
     *
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    /**
     * 计算日期间隔分钟数
     * @param one
     * @param two
     * @return
     */
    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数
     *
     * @param one
     * @param two
     *
     * @return 间隔天数
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 计算日期间隔天数算法
     * @param one
     * @param two
     * @return
     */
    public static int getDiffDays2(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (int) ((sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000));
    }

    /**
     * 忽略date的时间，计算日期间隔的天数（one-two）
     * @param one
     * @param two
     * @return
     */
    public static int getDiffDays3(Date one, Date two) {

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = parseDateNoTime(format(one, "yyyyMMdd"));
            date2 = parseDateNoTime(format(two, "yyyyMMdd"));
        } catch (ParseException e) {
            log.warn("时间转换时出错;", e);
            return -1;
        }

        return getDiffDays2(date1, date2);
    }

    /**
     * 通过某日期计算当前日期之前某条的日期，并以字符串形式返回
     * @param dateString
     * @param days
     * @return
     */
    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

        return df.format(date);
    }

    /**
     * 短格式日期字符串合法性校验
     * @param strDate
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 短日期格式校验
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     * 
     * @param strDate
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); //---- 避免日期中输入非数字 ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
     * 
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    /**
     * 日期转型-短日期格式
     * @param strDate
     * @return
     */
    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    /**
     * 替掉日期中特定字符，使日期转为短日期格式，并校验日期合法性
     * @param strDate
     * @param delimiter
     * @return
     */
    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtil.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    /**
     * 得到短格式型当月第一天日期字符串
     * @return
     */
    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    /**
     * 得到WEB所需格式的当前日期
     * @return
     */
    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(webFormat);

        return df.format(new Date());
    }

    /**
     * 以web日期格式输出当月的第一天字符串
     * @return
     */
    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(webFormat);

        return df.format(cal.getTime());
    }

    /**
     * String类型日期转型公共，通过一种日期格式转换成另外一种所需的String类型日期格式
     * @param dateString
     * @param formatIn
     * @param formatOut
     * @return
     */
    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            log.warn("convert() --- orign date error: " + dateString);
            return "";
        }
    }

    /**
     * 短日期转为网页显示所需日期格式
     * @param dateString
     * @return
     */
    public static String convert2WebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * 短日期转为中文风格日期
     * @param dateString
     * @return
     */
    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * 网页日期格式转为短日期格式
     * @param dateString
     * @return
     */
    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df2, df1);
    }

    /**
     * 时间先后判断算法
     * @param date1
     * @param date2
     * @return
     */
    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(webFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * 时间先后判断算法
     * @param date1
     * @param date2
     * @param dateWebFormat2
     *
     * @return
     */
    public static boolean dateNotLessThan(String date1, String date2, DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            if (log.isDebugEnabled()) {
                log.debug("dateNotLessThan() --- ParseException(" + date1 + ", " + date2 + ")");
            }
            return false;
        }
    }

    /**
     * 日期格式化："yyyy年MM月dd日HH:mm:ss"
     * @param today
     * @return
     */
    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * 日期格式化："MM月dd日HH:mm"
     * @param today
     * @return
     */
    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * 时间格式化
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static String formatTimeRange(Date startDate, Date endDate, String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range / DateUtils.MILLIS_PER_DAY;
        long hour = (range % DateUtils.MILLIS_PER_DAY) / DateUtils.MILLIS_PER_HOUR;
        long minute = (range % DateUtils.MILLIS_PER_HOUR) / DateUtils.MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    /**
     * 日期格式化："yyyyMM"
     * @param date
     * @return
     */
    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * 获取系统日期的前一天日期，返回Date
     *
     * @return
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * 获得指定时间当天起点时间
     * 
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);

        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            log.warn("ParseException : ", e);
            return date;
        }
    }

    /**
     * 得到一天中的最后一秒时间
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getDayEnd(Date date) {
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }

    /**获得当前月的最后一天
     * 
     * @param sDate1
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getLastDayOfMonth(Date sDate) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(sDate);
        int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        lastDate.setHours(23);
        lastDate.setMinutes(59);
        lastDate.setSeconds(59);
        return lastDate;
    }

    /**获得当前月的第一天
     * 
     * @param sDate1
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getFirstDayOfMonth(Date sDate) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(sDate);
        Date firstDayDate = cDay.getTime();
        firstDayDate.setDate(1);
        firstDayDate.setHours(0);
        firstDayDate.setMinutes(0);
        firstDayDate.setSeconds(0);
        return firstDayDate;
    }
}
