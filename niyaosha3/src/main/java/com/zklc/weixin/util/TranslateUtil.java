package com.zklc.weixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * 翻译辅助类
 * </p>
 * <p>
 * Copyright 北京中科联诚软件有限公司 2014 All right reserved.
 * </p>
 * 
 * @author jazz 时间 2014-1-13 下午5:23:43
 * @version 1.0 </br> 最后修改人 无
 */
public class TranslateUtil {
	public static Logger log = LoggerFactory.getLogger(TranslateUtil.class);
	public static String baiduFanyiKey="rU7SztOIMkhwPdaZm2eZOtLG";
	public static String baiduFanyiURL="http://openapi.baidu.com/public/2.0/bmt/translate?client_id=";
	public static String translateTip(){
	    return "欢迎使用翻译服务,使用方式如下：\n请发送'翻译 要翻译的内容'给我，(注意：翻译后留一个空格)\n赶快试试吧！";
	}
}