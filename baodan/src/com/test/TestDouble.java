package com.test;

import java.math.BigDecimal;

public class TestDouble {

	public static void main(String[] args) {
		Double d = 0.5;
		Double double1 = -5.0;
		System.out.println(getInt(d));
		System.out.println(getInt(double1));
	}
	
	public static int getInt(double number){
	    BigDecimal bd=new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
	    return Integer.parseInt(bd.toString()); 
	} 

}
