package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class TestFastDFS {
	
	public static String conf_filename = "d:/fdfs_client.conf";
	
	public static void main(String[] args) {
//		testUpload();
//		System.out.println(new Date(Calendar.getInstance().getTimeInMillis() - (1000*60*60*24*29)));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 29);
		System.out.println("增加月份后的日期："+calendar.getTime());
	}
	
	
	 public static void testUpload() {
	        try { 
	            ClientGlobal.init(conf_filename);
	 
	            TrackerClient tracker = new TrackerClient(); 
	            TrackerServer trackerServer = tracker.getConnection(); 
	            StorageServer storageServer = null;
	 
	            StorageClient storageClient = new StorageClient(trackerServer, storageServer); 
//	          NameValuePair nvp = new NameValuePair("age", "18"); 
	           /* NameValuePair nvp [] = new NameValuePair[]{ 
	                    new NameValuePair("age", "18"), 
	                    new NameValuePair("sex", "male") 
	            }; 
	            */
	            
	          //设置元信息  
	          NameValuePair[] metaList = new NameValuePair[3];  
	          metaList[0] = new NameValuePair("fileName", "test");  
	          metaList[1] = new NameValuePair("fileExtName", "jpg");  
	          metaList[2] = new NameValuePair("fileLength", "100");  
	          
	          File file = new File("c:/test.jpg");
	          
	          
	            String fileIds[] = storageClient.upload_file("d:/123.jpg", null, null);
	            
	            
	            System.out.println(fileIds.length); 
	            System.out.println("组名：" + fileIds[0]); 
	            System.out.println("路径: " + fileIds[1]);
	 
	        } catch (FileNotFoundException e) { 
	            e.printStackTrace(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } catch (MyException e) { 
	            e.printStackTrace(); 
	        } 
	    }

}
