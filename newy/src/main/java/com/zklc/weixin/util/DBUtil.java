package com.zklc.weixin.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;


/**
 * 
 * <p>
 * 用户辅助类
 * </p>
 * <p>
 * Copyright 北京中科联诚软件有限公司 2013 All right reserved.
 * </p>
 * 
 * @author jazz 时间 2013-8-28 下午7:16:02
 * @version 1.0 </br> 最后修改人 无
 */
public class DBUtil {
    public InputStream fis;
    public FileOutputStream  fos ;
    public String filePath; 
    private static  Properties  userProperties=new Properties();  
    private static DBUtil instance;
    public static DBUtil getInstance(){
           if(instance==null){
               instance=new DBUtil();
           }
           return instance;
       }
    public DBUtil() {
      
    	filePath = System.getProperty("user.dir");
    	System.out.println(filePath);
}
    
}
