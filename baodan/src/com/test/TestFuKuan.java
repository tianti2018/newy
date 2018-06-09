package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.SortedMap;

import com.tw.web.util.WeiXinFuKuanUtil;

public class TestFuKuan {

	public static void main(String[] args) {

		String billNo = WeiXinFuKuanUtil.createBillNo();
		SortedMap<String, String> map = WeiXinFuKuanUtil.createMap(billNo, "odfnS1BcSm7gBmRaUyc_ucQla-Ow", null,1);  
		WeiXinFuKuanUtil.sign(map);  
		String requestXML = WeiXinFuKuanUtil.getRequestXml(map);  
		try {
			String path="/shanrenwuyu.p12";
			if(System.getProperty("os.name").toLowerCase().contains("windows")) {
				path="c:/shanrenwuyu.p12";
			}
			 FileInputStream instream = new FileInputStream(new File(path));
			 String responseXML = WeiXinFuKuanUtil.postFuQian(requestXML,instream);
			 System.out.println(responseXML);
		} 
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
