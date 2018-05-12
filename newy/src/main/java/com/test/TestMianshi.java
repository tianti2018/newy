package com.test;

import java.util.ArrayList;
import java.util.List;

public class TestMianshi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(test1(10));
	}
	
	public static String test1(int n){
		if(n<=2){
			return "0,1";
		}else {
			List<Integer> list = new ArrayList<Integer>();
			list.add(0);
			list.add(1);
			for(int i=2;i<n;i++){
				int sum = 0;
				sum =list.get(i-2)+list.get(i-1);
				list.add(sum);
			}
			return list.toString();
		}
		
	}

}
