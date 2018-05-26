package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {

	public static void main(String[] args) throws ParseException{
		Date date = new Date();
		SimpleDateFormat   formatter   = 
				new   SimpleDateFormat("yyyy-MM-dd");
		System.out.println(formatter.format(date));
		System.out.println(formatter.format(date)+" 00:00:00");
		Date now = formatter.parse("2018-5-21 00:00:00");
		System.out.println(formatter.format(now));
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 1);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		
		System.out.println(calendar.getTime());
		System.out.println(formatter.format(calendar.getTime()));
		Double double1 = 489765465456446.2;
		java.text.NumberFormat nf = java.text.NumberFormat.getInstance();

		// 不使用千分位，即展示为11672283.234，而不是11,672,283.234

		nf.setGroupingUsed(false);

		// 设置数的小数部分所允许的最小位数

		nf.setMinimumFractionDigits(0);

		// 设置数的小数部分所允许的最大位数

		nf.setMaximumFractionDigits(5);

		System.out.println(nf.parse(double1.toString()));

		System.out.println(nf.format(double1)); // 11687337.2343
//		System.out.println(nf.format(nf.parse("11687337.23430").doubleValue())); // 11687337.2343
		System.out.println(double1.compareTo(0.2));
		System.out.println(double1.longValue());
		System.out.println(double1);
		System.out.println(Double.valueOf(double1));
		double1 = 10.0;
		System.out.println(double1%10);
		
		Long long1 = 123456789797654654l;
		System.out.println(long1);
	}
}
