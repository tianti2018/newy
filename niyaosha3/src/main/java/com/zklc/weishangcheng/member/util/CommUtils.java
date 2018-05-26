package com.zklc.weishangcheng.member.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class CommUtils {
	private static final int dataT = 5;

	public static int parentId(int x) {
		int z = (x - 1) / dataT;
		int z1 = (x - 1) % dataT > 0 ? 1 : 0;
		return z + z1;
	}

	public static String getSerialNumber(List list) {
		int maxnum = list.size();
		int lastnum = maxnum + 1;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowdate = sdf.format(new Date());
		String serialnumber = String.format("%1$,06d", lastnum);

		String materimaterinum = nowdate + serialnumber;
		return materimaterinum;
	}

	public static String getZBSerialNum(List list) {
		int maxnum = list.size();
		int lastnum = maxnum + 1;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowdate = sdf.format(new Date());
		String serialnumber = String.format("%1$,05d", lastnum);

		String zbserialNum = "ZB-" + nowdate + "-" + serialnumber;

		return zbserialNum;
	}

	public boolean isNullorEmpty(Object obj) {
		if (obj instanceof String) {
			if (StringUtils.isNotEmpty(String.valueOf(obj)) && (obj != null)) {
				return true;
			} else {
				return false;
			}
		} else if (obj instanceof Date) {
			if (obj != null) {
				return true;
			} else {
				return false;
			}
		}

		else if (obj instanceof Integer) {
			if (obj != null) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getSixRandom() {
		Random rad = new Random();
		String randS = "";
		for (int i = 0; i < 6; i++) {
			randS += rad.nextInt(10);
		}
		return randS;
	}

	public static void main(String args[]) {
		// 得到本地的缺省格式
		DecimalFormat df1 = new DecimalFormat("800000");
		// System.out.println(df1.format(10));
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmSS");
		String sf1 = sf.format(new Date());
		for (int i = 0; i < 472; i++) {
			System.out.println(88 + sf1 + getSixRandom());
			// System.out.println(getSixRandom());
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		// System.out.println(fd);

	}

}
