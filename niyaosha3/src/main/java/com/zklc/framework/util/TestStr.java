package com.zklc.framework.util;

import java.util.Date;
import java.util.Random;

public class TestStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for (int i = -40; i < 0; i++) {
			System.out.println(-new Random().nextInt(50));
			System.out.println(DateUtil.addDay(new Date(),i).toLocaleString());
		}
	}

}
