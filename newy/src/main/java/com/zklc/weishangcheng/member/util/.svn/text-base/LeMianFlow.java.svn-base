package com.zklc.weishangcheng.member.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;


public class LeMianFlow {
	public static void main(String[] args) {
		
		setOrderHuashi("15801073914","LLY10086010011");
		
		//订购流量
		//System.out.println(setOrderFlow("15171731881","2048"));//18580282719  13275386697
	//	getQueryOrder();
		
		/*try {
			dataPlan("15801073914", "10", "100001");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			checkData();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}
	
	
	
	/**
	 * 华时流量充值
	 * @param iphone 手机号
	 * @param lnumber 充值流量数值
	 * @return
	 */
	public static String setOrderHuashi(String iphone,String flowVlaue)
	{
		String result="";
		try {
		
			//基本参数
            String host = "http://121.42.53.157:8080/api/charge";
          
            String appkey = "1456903654168";  //我公司提供
            String appsecret = "27090ad95b008b54aef56160e62c0901";//我公司提供
            String mobile = iphone;
            String product_code = flowVlaue; //电信5M流量包

            String param = "appkey="+appkey+"&appsecret="+appsecret+"&mobile="+mobile+"&product_code="+product_code+"&range=0";
            
            result= sendGet(host,param);
          
           System.out.println(result);
			return result;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param iphone 手机号
	 * @param lnumber 充值流量数值
	 * @return
	 */
	public static String setOrderFlow(String iphone,String flowVlaue)
	{
		String result="";
		try {
		
			//基本参数
            String host = "http://sdk.le-mian.com/JsonApi.ashx";
            String stamp = new SimpleDateFormat("MMddHHmmss").format(new Date());

            String userId = "3532";  //我公司提供
            String userName = java.net.URLEncoder.encode("fyrh","utf-8");//我公司提供
            String password = md5("fyrh123" + stamp);//我公司提供
            String mobile = iphone;
            String flow = flowVlaue; //电信5M流量包

            //组合需要加密的字符串
            String strAes= String.format("%s,%s,%s,%s,%s,%s",
                userId, userName, password,mobile, flow, stamp);
            //加密
            String secret = md5(strAes);

            String strParam = String.format(
                    "UserId=%s&UserName=%s&Password=%s&mobile=%s&flow=%s&stamp=%s&secret=%s",
                    userId, userName, password,mobile, flow, stamp, secret);
            
            result= sendGet(host,strParam);
          
          // System.out.println(result);
			return result;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 云通信
	 * 2.流量直充（流量充值）
	 *  .接口参数：
	 *  appId->应用id，
	 *  phoneNum->充值手机号，
	 *  sn->套餐编号，
	 *  packet->套餐包大小
	 *  reason->充值说明，
	 *  customId->客户自定义业务id（要求唯一且长度不能超过32位），
	 *  callbackUrl->流量充值状态变更回调地址
	 */
	
	public static String dataPlan(String iphone,String flowVlaue,String sn ) throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		String url	   = "https://app.cloopen.com:8883/2013-12-26/Accounts/";
		String accountId = "aaf98f894e7826aa014e858929e109ab";	// 主账户id
		String token     = "54c020e2de514204b92a3bfa2702454b";	// 应用id
		url += accountId+"/flowPackage/flowRecharge";
		
		/*  statusCode	String	必选	请求状态码，取值000000（成功）	0000
		statusMsg	String	必选	对状态码的描述	获取成功
		rechargeId	String	必选	流量网关产生的唯一业务标识id	695136f5028d11e5a1610050568e55bd
		status	String	必选	流量充值的结果1,充值中3,成功4,失败	1
		customId	String	必选	第三方交易id 	695136f5028d11e5a1610050568e5500*/
		
		// json参数
		JSONObject json = new JSONObject();
		json.put("appId","aaf98f895190515201519065fa300017");
		json.put("phoneNum",iphone);
		json.put("sn",sn);
		json.put("packet",flowVlaue);
		json.put("reason","充值");
		json.put("customId","695136f5028d11e5a1610050568e55bd");
		json.put("callbackUrl","");
		
		String jsonBody=String.valueOf(json);
		String jsonResponse=HttpPostUtil.sendJSON(accountId, token, url, jsonBody);
		jsonResponse = "["+jsonResponse.substring(jsonResponse.indexOf("{"))+"]";
		System.out.println(jsonResponse);
		//status=200;conResult={"statusCode":"000000","customId":"695136f5028d11e5a1610050568e55bd","statusMsg":"请求发送成功","rechargeId":"13f9a8989a7247f286c59d6f26de201c","status":"1"}
		return jsonResponse;
		

	
	}
	
	/**
	 * 云通信
	 * 3.流量充值状态查询
	 *  .接口参数：
	 *  appId->应用Id
	 *  rechargeId->充值订单id
	 *  customId->客户自定义业务id（要求长度不能超过32位）
	 */
	public static String checkData() throws ClientProtocolException, NoSuchAlgorithmException, IOException{
		
		
		String url	   = "https://app.cloopen.com:8883/2013-12-26/Accounts/";
		String accountId = "aaf98f894e7826aa014e858929e109ab";	// 主账户id
		String token     = "54c020e2de514204b92a3bfa2702454b";	// 应用id
		//url += accountId+"/flowPackage/flowRecharge";
		//status=200;conResult={"statusCode":"000000","statusMsg":"充值成功","status":"3"}
		 url += accountId+"/flowPackage/flowRechargeStatus";
		JSONObject json = new JSONObject();
		json.put("appId","aaf98f895190515201519065fa300017");
		json.put("rechargeId","9bf037b2f5c44fbc844b6760cae8a91f");
		json.put("customId","695136f5028d11e5a1610050568e55bd");
	
		String jsonBody=String.valueOf(json);
		String jsonResponse=HttpPostUtil.sendJSON(accountId, token, url, jsonBody);
		jsonResponse = "["+jsonResponse.substring(jsonResponse.indexOf("{"))+"]";
		/*JSONArray jsonArr = JSONArray.fromObject("["+jsonResponse+"]");
        JSONObject jet = jsonArr.getJSONObject(0);*/
		System.out.println(jsonResponse);
		return jsonResponse;

	
	}
	
	
	
	
	
	//订单查询，使用可多次查询接口做样例
	private static String getQueryOrder()
	{
		String result="";
		try {
			 //基本参数
	        String host = "http://sdk.le-mian.com/JsonStatus.ashx";
	        String userId = "3532";  //我公司提供
	        String userName= java.net.URLEncoder.encode("fyrh","utf-8");//我公司提供
      
	        String password = md5("fyrh123");//我公司提供
	
	        String strParam = String.format("UserId=%s&UserName=%s&Password=%s",userId, userName, password);
	        System.out.println(strParam);
	
	        result= sendGet(host,strParam);
	
	        return result;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	private final static String md5(String s) {   
		    char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',   
		            'a', 'b', 'c', 'd', 'e', 'f' };   
		    try {   
		        byte[] strTemp = s.getBytes();   
		        MessageDigest mdTemp = MessageDigest.getInstance("MD5");   
		        mdTemp.update(strTemp);   
		        byte[] md = mdTemp.digest();   
				int j = md.length;   
		        char str[] = new char[j * 2];   
		        int k = 0;   
		        for (int i = 0; i < j; i++) {   
		           byte byte0 = md[i];   
		            str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
		            str[k++] = hexDigits[byte0 & 0xf];   
		        }   
		        return new String(str);   
		    } catch (Exception e) {   
		        e.printStackTrace();   
		        return null;   
		    }   
		} 
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
           
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
        return result;
    }


}
