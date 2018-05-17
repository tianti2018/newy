package com.zklc.framework.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 * @author qf
 */
public class CommonUtil {
	public static String ONLINE_MESSAGE_TYPE="OnlineMessageType";
	public static String SCALE_KEY="scale";
	public static String NATURE_KEY="nature";
	public static String EDUCATION_KEY="xueli";
	public static String DEGREE_KEY ="xuewei";
	public static String SEX_KEY="sex";
	public static String HY_KEY="shifouhaiwai";
	public static String QZ_KEY="qiuzhizhuangtai";
	public static String CARDTYPE_KEY="zhengjiantype";
	public static String QY_KEY="qiyong";
	public static String NATIONAL_KEY="national";
	public static String ZZ_KEY="zhengzhi";
	public static String HYZT_KEY="hunyinzhuangtai";
	public static String OTHER="其他";
	public static String DELEDATE_STATUS="1";
	//首页取数据条数
	public static int PAGE_SIZE=10;
	public static int PAGE_NO=1;
	public static String FIRST_PID="-1";
	public static String TOP_ADD="--";
	public static int NEW_DATE=2;//是否展示new的时间限制
	//导航菜单
	public static final String MENUID_NEWCENTER= "3";// 新闻中心
	public static final String MENUID_YCFWWB= "11";// 永川服务外包菜单
	public static final String MENUID_NEWSHOT = "12"; // 新闻热点
	public static final String MENUID_NOTICE = "13";  // 通知公告
	public static final String MENUID_GUOJIAZC = "14";// 国家政策
	public static final String MENUID_CHONGQINGZC = "15";// 重庆政策
	public static final String MENUID_YUANQUZC = "16"; // 园区政策
	public static final String MENUID_PEIXUNORG = "17";// 培训机构 
	public static final String MENUID_PEIXUNZC = "18"; // 培训政策 
	public static final String MENUID_PEIXUNINFO = "19";// 培训信息 
	public static final String MENUID_SHIXIBASE = "20"; // 实习基地 
	public static final String MENUID_SHIXIINFO = "21";//  实习信息
	public static final String MENUID_WAIBAOSCHOOL = "22";// 外包学堂
	public static final String MENUID_WAIBAOZHINAN = "23";// 服务外包指南
	public static final String MENUID_QYZS = "24";//企业展示
	public static final String MENUID_MEDIA_NOTICE = "50";//媒体报道
	public static final String MENUID_ZCFG = "4";//政策法规
	
	
	//角色id
	public static String GARDEN="000001";
	public static String COMPANY="000002";
	public static String PERSONAL="000003";
	public static String COLLEGE="000004";
	public static String TEACHER="000005";

	public static boolean isNull(Object str){
		if(str!=null&&!str.toString().trim().equals("")){
			return false;
		}else{
			return true;
		}
	}
	public static boolean isNull(String str){
		if(str!=null&&str.trim().length()!=0){
			return false;
		}else{
			return true;
		}
	}
	public static boolean isNull(Integer str){
		if(str!=null&&str.intValue()!=0){
			return false;
		}else{
			return true;
		}
	}
	public static boolean isNull(List list){
		if(list!=null&&list.size()!=0){
			return false;
		}else{
			return true;
		}
	}
	public static String findUserType(String type){
		if(isNull(type)){
			return type;
		}else{
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("gryh", "Personal");
			map.put("qyyh", "Company");
			map.put("yxyh", "College");
			map.put("jsyh", "Teacher");
			return (String) map.get(type);
		}
	}
	public static String joinDicKey(String key){
		return "parkms_"+key;
	}

	public static String clobToString(Clob clob){  
       if(clob == null) {  
           return null;  
       }  
       try{  
           Reader inStreamDoc = clob.getCharacterStream();  
           char[] tempDoc = new char[(int) clob.length()];  
           inStreamDoc.read(tempDoc);  
           inStreamDoc.close();  
           return new String(tempDoc);  
       }catch (IOException e){  
           e.printStackTrace();  
       }catch (SQLException es){  
           es.printStackTrace();  
       }  
         return null;  
     }
	public static boolean isNew(Date date){
		try{
			if(date!=null){
				Date newDate=new Date();
				if(date.getTime()>newDate.getTime()&&(date.getTime()-newDate.getTime()<= (NEW_DATE*24L*60*60*1000))){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
}
