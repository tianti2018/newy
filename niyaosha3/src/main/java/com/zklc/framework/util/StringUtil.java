package com.zklc.framework.util;

import java.util.List;

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
	
	/**
     * 转换sql参数   aaa,bbbb,bbb==>  "aaa","bbb","ccc"
     */
    public static String replaceString(String param){
        StringBuilder sb = new StringBuilder();
        String para[] =param.split(",");
        for(int i =0;i<para.length;i++){
            sb.append("'"+para[i]+"',");
        }
        String aa = sb.toString();
        return aa.substring(0, aa.lastIndexOf(","));
    }
    
    /**
     * 转换sql参数   aaa,bbbb,bbb==>  "aaa","bbb","ccc"
     */
    public static String replaceString(List param){
        StringBuilder sb = new StringBuilder();
        
        for(int i =0;i<param.size();i++){
            sb.append("'"+param.get(i).toString()+"',");
        }
        String aa = sb.toString();
        if(!"".equals(aa)){
            return aa.substring(0, aa.lastIndexOf(","));
        }else{
            return "";
        }
        
    }
}
