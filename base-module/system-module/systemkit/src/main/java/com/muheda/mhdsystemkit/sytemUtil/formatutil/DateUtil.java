package com.muheda.mhdsystemkit.sytemUtil.formatutil;

import android.content.Context;

import com.muheda.mhdsystemkit.sytemUtil.functionutil.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by WF
 */
public final class DateUtil {

    private DateUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 时间日期格式化到年月日时分秒.
     */
    public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMD = "yyyy-MM-dd";

    /**
     * 时间日期格式化到年月.
     */
    public static final String dateFormatYM = "yyyy-MM";

    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";
    public static final String dateFormatMDHM = "MM-dd HH:mm";

    /**
     * 时间日期格式化到月日.
     */
    public static final String dateFormatMD = "MM/dd";

    /**
     * 时分秒.
     */
    public static final String dateFormatHMS = "HH:mm:ss";

    /**
     * 时分.
     */
    public static final String dateFormatHM = "HH:mm";

    public static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    public static Calendar getCalendar() {
        return new GregorianCalendar();
    }


    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format  输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        return getStringByOffset(strDate, format, 0, 0);
    }

    /**
     * 描述：获取指定日期时间的字符串(可偏移).
     *
     * @param strDate       String形式的日期时间
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getStringByOffset(String strDate, String format, int calendarField, int offset) {
        String mDateTime = null;
        try {
            Calendar c = getCalendar();
            SimpleDateFormat mSimpleDateFormat = getFormat(format);
            c.setTime(mSimpleDateFormat.parse(strDate));
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：获取表示当前日期时间的字符串.
     *
     * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String String类型的当前日期时间
     */
    public static String getCurrentDate(String format) {
        return getStringByFormat(getCalendar().getTime(), format);
    }

    /**
     * 描述：Date类型转化为String类型.
     *
     * @param date   the date
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        return getStringByOffset(date, format, 0, 0);
    }

    /**
     * 描述：获取表示当前日期时间的字符串(可偏移).
     *
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getCurrentDateByOffset(String format, int calendarField, int offset) {
        return getStringByOffset(getCalendar().getTime(), format, calendarField, offset);

    }

    /**
     * 获取表示当前日期时间的字符串
     *
     * @return
     */
    public static String getCurrentDateByOffset() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = format.format(new Date());
        return date;
    }

    /**
     * 描述：Date类型转化为String类型(可偏移).
     *
     * @param date          the date
     * @param format        the format
     * @param calendarField the calendar field
     * @param offset        the offset
     * @return String String类型日期时间
     */
    public static String getStringByOffset(Date date, String format, int calendarField, int offset) {
        String strDate = null;
        try {
            SimpleDateFormat mSimpleDateFormat = getFormat(format);

            Calendar c = getCalendar();
            c.setTime(date);
            c.add(calendarField, offset);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    /**
     * 描述：获取milliseconds表示的日期时间的字符串.
     *
     * @param milliseconds the milliseconds
     * @param format       格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 日期时间字符串
     */
    public static String getStringByFormat(long milliseconds, String format) {
        String thisDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisDateTime;
    }


    /**
     * 描述：获取本周一.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.MONDAY);
    }

    /**
     * 描述：获取本周日.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.SUNDAY);
    }

    /**
     * 描述：获取本周的某一天.
     *
     * @param format        the format
     * @param calendarField the calendar field
     * @return String String类型日期时间
     */
    private static String getDayOfWeek(String format, int calendarField) {
        String strDate = null;
        try {
            Calendar c = getCalendar();
            SimpleDateFormat mSimpleDateFormat = getFormat(format);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == calendarField) {
                strDate = mSimpleDateFormat.format(c.getTime());
            } else {
                int offectDay = calendarField - week;
                if (calendarField == Calendar.SUNDAY) {
                    offectDay = 7 - Math.abs(offectDay);
                }
                c.add(Calendar.DATE, offectDay);
                strDate = mSimpleDateFormat.format(c.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取本月第一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = getCalendar();
            SimpleDateFormat mSimpleDateFormat = getFormat(format);
            //当前月的第一天
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;

    }

    /**
     * 描述：获取本月最后一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = getCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的最后一天
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取表示当前日期的0点时间毫秒数.
     *
     * @return the first time of day
     */
    public static long getFirstTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 00:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 描述：获取表示当前日期24点时间毫秒数.
     *
     * @return the last time of day
     */
    public static long getLastTimeOfDay() {
        Date date = null;
        try {
            String currentDate = getCurrentDate(dateFormatYMD);
            date = getDateByFormat(currentDate + " 24:00:00", dateFormatYMDHMS);
            return date.getTime();
        } catch (Exception e) {
        }
        return -1;
    }

    /**
     * 描述：判断是否是闰年()
     * <p>(year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
     *
     * @param year 年代（如2012）
     * @return boolean 是否为闰年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 || year % 400 == 0;
    }

    /**
     * 取指定日期为星期几.
     *
     * @param strDate  指定日期
     * @param inFormat 指定日期格式
     * @return String   星期几
     */
    public static String getWeekNumber(String strDate, String inFormat) {
        String week = "星期日";
        Calendar calendar = getCalendar();
        DateFormat df = getFormat(inFormat);
        try {
            calendar.setTime(df.parse(strDate));
        } catch (Exception e) {
            return "错误";
        }
        int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (intTemp) {
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        return week;
    }

    /**
     * 根据给定的日期判断是否为上下午.(24小时)
     *
     * @param strDate the str date
     * @param format  the format
     * @return the time quantum
     */
    public static String getTimeQuantum(String strDate, String format) {
        Date mDate = getDateByFormat(strDate, format);
        int hour = mDate.getHours();
        if (hour >= 12)
            return "PM";
        else
            return "AM";
    }

    /**
     * 根据给定的毫秒数算得时间的描述.
     *
     * @param milliseconds the milliseconds
     * @return the time description
     */
    public static String getTimeDescription(long milliseconds) {
        if (milliseconds > 1000) {
            //大于一分
            if (milliseconds / 1000 / 60 > 1) {
                long minute = milliseconds / 1000 / 60;
                long second = milliseconds / 1000 % 60;
                return minute + "分" + second + "秒";
            } else {
                //显示秒
                return milliseconds / 1000 + "秒";
            }
        } else {
            return milliseconds + "毫秒";
        }
    }

    /**
     * 描述：根据时间返回格式化后的时间的描述.
     * 小于1小时显示多少分钟前  大于1小时显示今天＋实际日期，大于今天全部显示实际时间
     *
     * @param strDate   the str date
     * @param outFormat the out format
     * @return the string
     */
    public static String formatDateStr2Desc(String strDate, String outFormat) {

        DateFormat df = getFormat(dateFormatYMDHMS);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c2.setTime(df.parse(strDate));
            c1.setTime(new Date());
            int d = getOffectDay(c1.getTimeInMillis(), c2.getTimeInMillis());
            if (d == 0) {
                int h = getOffectHour(c1.getTimeInMillis(), c2.getTimeInMillis());
                if (h > 0) {
                    return "今天" + getStringByFormat(strDate, dateFormatHM);
                    //return h + "小时前";
                } else if (h < 0) {
                    //return Math.abs(h) + "小时后";
                } else if (h == 0) {
                    int m = getOffectMinutes(c1.getTimeInMillis(), c2.getTimeInMillis());
                    if (m > 0) {
                        return m + "分钟前";
                    } else if (m < 0) {
                        //return Math.abs(m) + "分钟后";
                    } else {
                        return "刚刚";
                    }
                }

            } else if (d > 0) {
                if (d == 1) {
                    //return "昨天"+getStringByFormat(strDate,outFormat);
                } else if (d == 2) {
                    //return "前天"+getStringByFormat(strDate,outFormat);
                }
            } else if (d < 0) {
                if (d == -1) {
                    //return "明天"+getStringByFormat(strDate,outFormat);
                } else if (d == -2) {
                    //return "后天"+getStringByFormat(strDate,outFormat);
                } else {
                    //return Math.abs(d) + "天后"+getStringByFormat(strDate,outFormat);
                }
            }

            String out = getStringByFormat(strDate, outFormat);
            if (!StringUtils.isBlank(out)) {
                return out;
            }
        } catch (Exception e) {
        }

        return strDate;
    }

    /**
     * 描述：计算两个日期所差的天数.
     *
     * @param milliseconds1 the milliseconds1
     * @param milliseconds2 the milliseconds2
     * @return int 所差的天数
     */
    public static int getOffectDay(long milliseconds1, long milliseconds2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(milliseconds1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(milliseconds2);
        //先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 + maxDays;
        } else if (y1 - y2 < 0) {
            maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 - maxDays;
        } else {
            day = d1 - d2;
        }
        return day;
    }

    /**
     * 描述：计算两个日期所差的小时数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的小时数
     */
    public static int getOffectHour(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h = 0;
        int day = getOffectDay(date1, date2);
        h = h1 - h2 + day * 24;
        return h;
    }

    /**
     * 描述：计算两个日期所差的分钟数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static int getOffectMinutes(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int m1 = calendar1.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        int h = getOffectHour(date1, date2);
        int m = 0;
        m = m1 - m2 + h * 60;
        return m;
    }

    /**
     * 描述：String类型的日期时间转化为Date类型.
     *
     * @param strDate String形式的日期时间
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = getFormat(format);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 描述：获取偏移之后的Date.
     *
     * @param date          日期时间
     * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return Date 偏移之后的日期时间
     */
    public Date getDateByOffset(Date date, int calendarField, int offset) {
        Calendar c = getCalendar();
        try {
            c.setTime(date);
            c.add(calendarField, offset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c.getTime();
    }

    /**
     * 判断当前时间是否在某段时间段内
     *
     * @return final int start = 9 * 60;// 起始时间 09:00的分钟数
     * final int end = 21 * 60;// 结束时间 21:00的分钟数
     */
    public static boolean jugeTimeRange(int start, int end) {
        Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        int minute = cal.get(Calendar.MINUTE);// 获取分钟
        int minuteOfDay = hour * 60 + minute;// 从0:00分开是到目前为止的分钟数
        return minuteOfDay >= start && minuteOfDay <= end;
    }

    /**
     * 取得当月天数 roll只会回滚日期，不影响月
     */
    public static int getCurrentMonthDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);//把日期设置为当月第一天
        calendar.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = calendar.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取指定年月的天数
     *
     * @param month
     * @param year
     * @return
     */
    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return 31;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.FEBRUARY:
                return (year % 4 == 0) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    /**
     * 日期跟当前日期的比较 如果输入日期大于当前日期，返回false 小于或者等于返回true
     *
     * @param context
     * @param date
     * @return
     */
    public static boolean checkDate(Context context, String date) {
        SimpleDateFormat formatter = getFormat(dateFormatYMD);
        try {
            Date date1 = formatter.parse(date);
            int flag = date1.compareTo(new Date());

            // 返回 0 表示时间日期相同
            // 返回 1 表示日期1>日期2
            // 返回 -1 表示日期1<日期2

            if (flag == 1) {
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 将月份或者时间前面加0返回
     *
     * @param month
     * @return
     */
    public static String monthChange(int month) {
        StringBuffer str = new StringBuffer();
        if (month < 10) {
            str.append("0");
            str.append(month);
        } else {
            str.append(month);
        }
        return str.toString();
    }

    /**
     * 获得几天之前或者几天之后的日期
     *
     * @param diff 差值：正的往后推，负的往前推
     * @return
     */
    public static String getOtherDay(int diff) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DATE, diff);
        return getStringByFormat(mCalendar.getTime(), dateFormatYMD);
    }

    public static Calendar getCalendar(String strTime) {
        try {
            Date date = getFormat(dateFormatYMD).parse(strTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (Exception e) {
            return Calendar.getInstance();
        }
    }

    //获取最近的上周周一的时间
    public static Calendar getLastWeekOnMonday() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, -7);
        int day = instance.get(Calendar.DAY_OF_WEEK);
        day -= 2;
        instance.add(Calendar.DAY_OF_MONTH, -day);
        return instance;
    }
}
