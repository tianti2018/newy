package com.tw.web.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.tw.web.hibernate.persistent.Orders;
import com.tw.web.hibernate.persistent.Tixian;
import com.tw.web.hibernate.persistent.User;

public class HttpConnectionUtil {

	public static void sendHttpMsg(String type,User user,Tixian tixian,Orders orders,String refferOpenId){
		String requestUrl = SystemConfigUtil.getString("yuming")+"/sendMessageOrdersServlet";
		StringBuffer buffer = null;
		try {
			URL url = new URL(requestUrl);
			 HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
	            httpUrlConn.setRequestProperty("Charsert", "UTF-8");
	            httpUrlConn.setDoInput(true);
	            httpUrlConn.setRequestMethod("POST");
	            httpUrlConn.setDoOutput(true);
	            StringBuffer params = new StringBuffer();
	            System.out.println(requestUrl+"111111111111111111111111111111111111111111111111111111111111111111111111");
	            if(type != null && !type.isEmpty())
	            	params.append("type").append("=").append(type);
	            if(user != null){
//	            	params.append("&wxOpenId").append("=").append(user.getWxOpenid());
	            }
	            if(orders != null){
	            	params.append("&orderNo").append("=").append(orders.getOrdersBH());
	            	params.append("&kuaidiNo").append("=").append(orders.getKuaidiNo());
	            	params.append("&kuaidiName").append("=").append(orders.getKuaidiName());
	            }
	            if(tixian != null){
	            	params.append("&txMoney").append("=").append(tixian.getTixianMoney());
	            	params.append("&txDate").append("=").append(tixian.getAppDate());
	            }
	            if(refferOpenId != null){
	            	params.append("&refferOpenId").append("=").append(refferOpenId);
	            }
	            System.out.println(requestUrl+"22222222222222222222222222222222222222222222222222222222222222");	
	            byte[] bypes = params.toString().getBytes();
	            httpUrlConn.getOutputStream().write(bypes);
//	            httpUrlConn.connect();
	            InputStream inputStream = httpUrlConn.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            System.out.println(requestUrl+"33333333333333333333333333333333333333333333333333333333333333333");
	            buffer = new StringBuffer();
	            String str = null;
	            while ((str = bufferedReader.readLine()) != null) {
	                buffer.append(str);
	            }
	            bufferedReader.close();
	            inputStreamReader.close();
	            inputStream.close();
	            httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
