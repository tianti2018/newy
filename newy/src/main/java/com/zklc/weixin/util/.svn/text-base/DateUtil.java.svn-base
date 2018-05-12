package com.zklc.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;

import java.util.*;
import java.text.*;

public class DateUtil {

	public DateUtil() {
	}

	/**
	 * 格式化日期为字符串 "yyyy-MM-dd   hh:mm"
	 * 
	 * @param basicDate
	 *            日期对象
	 * @param strFormat
	 *            日期格式
	 * @return
	 */
	public static String formatDateTime(Date basicDate, String strFormat) {
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		return df.format(basicDate);
	}

	/**
	 * 格式化日期为字符串 "yyyy-MM-dd   hh:mm"
	 * 
	 * @param basicDate
	 *            日期对象
	 * @param strFormat
	 *            日期对象
	 * @return
	 */
	public static String formatDateTime(String basicDate, String strFormat) {
		SimpleDateFormat df = new SimpleDateFormat(strFormat);
		Date tmpDate = null;
		try {
			tmpDate = df.parse(basicDate);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}
		return df.format(tmpDate);
	}

	/**
	 * 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
	 * 
	 * @param n
	 * @return
	 */
	public static String nDaysAftertoday(int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		// rightNow.add(Calendar.DAY_OF_MONTH,-1);
		rightNow.add(Calendar.DAY_OF_MONTH, +n);
		return df.format(rightNow.getTime());
	}

	/**
	 * 当前日期加减n天后的日期，返回String (yyyy-mm-dd)
	 * 
	 * @param n
	 * @return
	 */
	public static Date nDaysAfterNowDate(int n) {
		Calendar rightNow = Calendar.getInstance();
		// rightNow.add(Calendar.DAY_OF_MONTH,-1);
		rightNow.add(Calendar.DAY_OF_MONTH, +n);
		return rightNow.getTime();
	}

	/**
	 * 给定一个日期型字符串，返回加减n天后的日期型字符串
	 * 
	 * @param basicDate
	 * @param n
	 * @return
	 */
	public static String nDaysAfterOneDateString(String basicDate, int n) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date tmpDate = null;
		try {
			tmpDate = df.parse(basicDate);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}
		long nDay = (tmpDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
				* (24 * 60 * 60 * 1000);
		tmpDate.setTime(nDay);

		return df.format(tmpDate);
	}

	/**
	 * 给定一个日期，返回加减n天后的日期
	 * 
	 * @param basicDate
	 * @param n
	 * @return
	 */
	public static Date nDaysAfterOneDate(Date basicDate, int n) {
		long nDay = (basicDate.getTime() / (24 * 60 * 60 * 1000) + 1 + n)
				* (24 * 60 * 60 * 1000);
		basicDate.setTime(nDay);

		return basicDate;
	}

	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int nDaysBetweenTwoDate(Date firstDate, Date secondDate) {
		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	/**
	 * 计算两个日期相隔的小时数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int nHoursBetweenTwoDate(Date firstDate, Date secondDate) {
		int nHours = (int) ((secondDate.getTime() - firstDate.getTime()) / (60 * 60 * 1000));
		return nHours;
	}

	/**
	 * 计算两个日期相隔的分钟数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int nMinutesBetweenTwoDate(Date firstDate, Date secondDate) {
		int nMinutes = (int) ((secondDate.getTime() - firstDate.getTime()) / (60 * 60 * 60 * 1000));
		return nMinutes;
	}

	/**
	 * 计算两个日期相隔的天数
	 * 
	 * @param firstString
	 * @param secondString
	 * @return
	 */
	public static int nDaysBetweenTwoDate(String firstString,
			String secondString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date firstDate = null;
		Date secondDate = null;
		try {
			firstDate = df.parse(firstString);
			secondDate = df.parse(secondString);
		} catch (Exception e) {
			// 日期型字符串格式错误
		}

		int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return nDay;
	}

	public static String getbefore48Hours() {
		String tempStr = "";
		DateFormat dft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String nowDateStr = new Date().toLocaleString();
		String dateStr = nDaysAftertoday(-2);
		dateStr += " " + nowDateStr.split(" ")[1];
		try {
			tempStr = dft.parse(dateStr).toLocaleString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempStr;
	}

	public static void main(String[] args) {

		System.out.println(getbefore48Hours());
	}

}
