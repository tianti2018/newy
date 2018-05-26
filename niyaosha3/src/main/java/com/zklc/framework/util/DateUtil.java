package com.zklc.framework.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * 日期Util类
 */
public class DateUtil
{

    private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
    private static String[] dateFormat = {"yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy年MM月dd日HH时mm分ss秒", "yyyy-MM-dd", "yyyy/MM/dd", "yy-MM-dd", "yy/MM/dd", "yyyy年MM月dd日", "HH:mm:ss",
            "yyyyMMddHHmmss", "yyyyMMdd", "yyyy.MM.dd", "yy.MM.dd"};


    /**
     * 获得默认的date pattern
     */

    public static String getDatePattern()
    {

        return defaultDatePattern;
    }


    /**
     * 返回预设Format的当前日期字符串
     */
    public static String getToday()
    {

        Date today = new Date();
        return format(today);
    }


    /**
     * 返回预设Format格式化Date成字符串
     */
    private static String format(Date date)
    {

        return date == null ? "" : formate(date, getDatePattern());
    }


    /**
     * 使用参数Formate格式化Date成字符串
     */
    private static String formate(Date date, String datePattern)
    {

        return date == null ? "" : new SimpleDateFormat(datePattern).format(date);
    }


    /**
     * 使用预设格式将字符串转为Date
     */

    public static Date parse(String strDate) throws ParseException
    {

        System.out.println(strDate);
        return StringUtils.isBlank(strDate) ? null : parse(strDate, getDatePattern());
    }


    private static Date parse(String strDate, String datePattern) throws ParseException
    {

        return StringUtils.isAlpha(strDate) ? null : new SimpleDateFormat(datePattern).parse(strDate);
    }


    /**
     * 在日期上增加数个整月
     */
    public static Date addMonth(Date date, int n)
    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }
    /**
    * 在日期上增加天数
    */
   public static Date addDay(Date date, int n)
   {

       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       cal.add(Calendar.DAY_OF_MONTH, n);
       return cal.getTime();
   }
   /**
    * 在日期上增加秒
    */
   public static Date addSecond(Date date, int n)
   {

       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       cal.add(Calendar.SECOND, n);
       return cal.getTime();
   }
    /**
     * 取得指定月份的第一天
     * 
     * @param strdate
     *            String
     * @return String
     * @throws Exception
     */
    public String getMonthBegin(String strdate) throws Exception
    {

        java.util.Date date = parse(strdate);
        return formatDateByFormat(date, "yyyy-MM") + "-01";
    }


    /**
     * 取得指定月份的最后一天
     * 
     * @param strdate
     *            String
     * @return String
     * @throws Exception
     */
    public String getMonthEnd(String strdate) throws Exception
    {

        java.util.Date date = parse(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }


    /**
     * 常用的格式化日期
     * 
     * @param date
     *            Date
     * @return String
     */
    public String formatDate(java.util.Date date)
    {

        return formatDateByFormat(date, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 以指定的格式来格式化日期
     * 
     * @param date
     *            Date
     * @param format
     *            String
     * @return String
     */
    public String formatDateByFormat(java.util.Date date, String format)
    {

        String result = "";
        if(date != null)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch(Exception ex)
            {

                ex.printStackTrace();
            }
        }
        return result;
    }


    public static Timestamp convUtilCalendarToSqlTimestamp(Calendar date)
    {

        if(date == null)
            return null;

        return new Timestamp(date.getTimeInMillis());
    }


    public static Calendar convSqlTimestampToUtilCalendar(Timestamp date)
    {

        if(date == null)
            return null;

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(date.getTime());
        return gc;
    }


    public static Calendar parseDate(String dateStr)
    {

        if((dateStr == null) || (dateStr.trim().length() == 0))
            return null;

        Date result = parseDate(dateStr, 0);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);

        return cal;
    }


    public static String toDateTimeStr(Calendar date)
    {

        if(date == null)
            return null;
        return new SimpleDateFormat(dateFormat[0]).format(date.getTime());
    }


    public static String toDateStr(Calendar date)
    {

        if(date == null)
            return null;
        return new SimpleDateFormat(dateFormat[3]).format(date.getTime());
    }


    public static String toDateStr(Date date, int index)
    {

        if(date == null)
            return null;
        return new SimpleDateFormat(dateFormat[index]).format(date);
    }


    public static int calendarMinus(Calendar d1, Calendar d2)
    {

        if((d1 == null) || (d2 == null))
        {
            return 0;
        }

        d1.set(11, 0);
        d1.set(12, 0);
        d1.set(13, 0);

        d2.set(11, 0);
        d2.set(12, 0);
        d2.set(13, 0);

        long t1 = d1.getTimeInMillis();
        long t2 = d2.getTimeInMillis();
        long daylong = 86400000L;
        t1 -= t1 % daylong;
        t2 -= t2 % daylong;

        long t = t1 - t2;
        int value = (int)(t / daylong);

        return value;
    }


    public static Date parseDate(String dateStr, int index)
    {

        if(dateStr == null || "".equals(dateStr))
        {
            return null;
        }
        DateFormat df = null;
        try
        {
            df = new SimpleDateFormat(dateFormat[index]);

            return df.parse(dateStr);
        } catch(ParseException pe)
        {
            return parseDate(dateStr, index + 1);
        } catch(ArrayIndexOutOfBoundsException aioe)
        {
            return null;
        }
    }


    public static Date getNextDay(int day)
    {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
    
    public static Date getDateByDays(Integer days){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	public static String getDateStringByDays(Integer days){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(getDateByDays(days));
	}
	
	public static Date getLingDianDateByDays(Integer days){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static String getLingDianDateStringByDays(Integer days){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(getLingDianDateByDays(days))+" 00:00:00";
	}
}
