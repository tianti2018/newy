package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.SortedMap;

import com.tw.web.util.HongBaoUtil;

public class TestHongbao {

	public static void main(String[] args) {
		String billNo = HongBaoUtil.createBillNo();
		SortedMap<String, String> map = HongBaoUtil.createMap(billNo, "odfnS1BcSm7gBmRaUyc_ucQla-Ow", null,1);  
		HongBaoUtil.sign(map);  
		String requestXML = HongBaoUtil.getRequestXml(map);  
		try {
			String path="/shanrenwuyu.p12";
			if(System.getProperty("os.name").toLowerCase().contains("windows")) {
				path="c:/shanrenwuyu.p12";
			}
			 FileInputStream instream = new FileInputStream(new File(path));
			 String responseXML = HongBaoUtil.post(requestXML,instream);
			 System.out.println(responseXML);
		} 
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
