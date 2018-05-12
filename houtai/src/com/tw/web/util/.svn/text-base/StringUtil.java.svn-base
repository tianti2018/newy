package com.tw.web.util;

public class StringUtil {
	public static String returnPOColName(String dbColName){
		
		String [] strs=dbColName.split("_");
		String poColName="";
		for(int i=0;i<strs.length;i++){
			poColName =poColName+(strs[i].replaceFirst(strs[i].charAt(0) + "",
					new String(strs[i].charAt(0) + "").toUpperCase()));
		}
		return poColName;
		
	}
}
