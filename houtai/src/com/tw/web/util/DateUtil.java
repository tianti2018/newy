package com.tw.web.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

  /**
   * 日期Util类
   * */
  public class DateUtil {
	private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获得默认的date pattern
	 * */
	
	public static String getDatePattern(){
		return defaultDatePattern;
	}
	
	/**
	 * 返回预设Format的当前日期字符串
	 * */
	public static String getToday(){
		Date today = new Date();
		return format(today);
	}

	/**
	 * 返回预设Format格式化Date成字符串
	 * */
	private static String format(Date date) {
		
		return date == null ? "" : formate(date,getDatePattern());
	}

	/**
	 * 使用参数Formate格式化Date成字符串
	 * */
	private static String formate(Date date, String datePattern) {
		
		return date == null ? "" : new SimpleDateFormat(datePattern).format(date) ;
	}
	
	/**
	 * 使用预设格式将字符串转为Date
	 * */
	
	public static Date parse(String strDate) throws ParseException{
		System.out.println(strDate);
		return StringUtils.isBlank(strDate)?null : parse(strDate,getDatePattern());
	}

	private static Date parse(String strDate, String datePattern)throws ParseException {
		
		return StringUtils.isAlpha(strDate)?null:new SimpleDateFormat(datePattern).parse(strDate);
	}
	
	/**
	 * 在日期上增加数个整月
	 * */
	public static Date addMonth(Date date,int n){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
	
	 /**
   * 取得指定月份的第一天
   *
   * @param strdate String
   * @return String
	 * @throws Exception 
   */
  public String getMonthBegin(String strdate) throws Exception
  {
      java.util.Date date = parse(strdate);
      return formatDateByFormat(date,"yyyy-MM") + "-01";
  }

  /**
   * 取得指定月份的最后一天
   *
   * @param strdate String
   * @return String
   * @throws Exception 
   */
  public String getMonthEnd(String strdate) throws Exception
  {
      java.util.Date date = parse(getMonthBegin(strdate));
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.MONTH,1);
      calendar.add(Calendar.DAY_OF_YEAR, -1);
      return formatDate(calendar.getTime());
  }

  /**
   * 常用的格式化日期
   *
   * @param date Date
   * @return String
   */
  public String formatDate(java.util.Date date)
  {
      return formatDateByFormat(date,"yyyy-MM-dd HH:mm:ss");
  }

  /**
   * 以指定的格式来格式化日期
   *
   * @param date Date
   * @param format String
   * @return String
   */
  public String formatDateByFormat(java.util.Date date,String format)
  {
      String result = "";
      if(date != null)
      {
          try
          {
              SimpleDateFormat sdf = new SimpleDateFormat(format);
              result = sdf.format(date);
          }
          catch(Exception ex)
          {
            
              ex.printStackTrace();
          }
      }
      return result;
  }
  
  public static void main(String[] args) {
	DateUtil dateUtil = new DateUtil();
	System.err.println(dateUtil.addMonth(new Date(), 1));
  }
	
  }
