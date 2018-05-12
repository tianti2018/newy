package com.zklc.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zklc.weixin.pojo.AccessToken;

/**
 * 
 * <p>
 * 功能
 * </p>
 * <p>
 * Copyright 北京中科联诚软件有限公司 2014 All right reserved.
 * </p>
 * 
 * @author yueyong 时间 2014-5-22 下午4:15:33
 * @version 1.0 </br> 最后修改人 无
 */
public class WeixinUtil {
	public static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
	public static AccessToken accessToken = null;
	public static String  appId = SystemMessage.getString("APPID");
	public static String  appSecret = SystemMessage.getString("APPSECRET");

	/**
	 * 
	 * <p>
	 * 功能
	 * </p>
	 * @author yueyong 时间 2014-5-22 下午4:15:48
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		/*StringBuffer buffer = new StringBuffer();
		try {
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// ���������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ�
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			System.out.println(">>>>>>>>>>>> buffer.toString() "+buffer.toString());
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}*/
		if(requestMethod.equals("GET")){
			jsonObject = httpGet(requestUrl);
		}else if(requestMethod.equals("POST")){
			jsonObject = httpPost(requestUrl, outputStr);
		}
		return jsonObject;
	}
	
	/**
     * httpPost
     * @param url  路径
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url,String jsonParam){
        return httpPost(url, jsonParam, false);
    }
    
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url,JSONObject jsonParam, boolean noNeedResponse){
        //post请求返回结果
    	CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            //httpClient.getParams().setParameter("http.socket.timeout", new Integer(30000)); 
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity(),"UTF-8");
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
 
    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param noNeedResponse    不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url,String jsonParam, boolean noNeedResponse){
        //post请求返回结果
       // DefaultHttpClient httpClient = new DefaultHttpClient();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        JSONObject jsonResult = null;
        HttpPost method = new HttpPost(url);
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            //httpClient.getParams().setParameter("http.socket.timeout", new Integer(30000)); 
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == 200) {
                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity(),"UTF-8");
                    if (noNeedResponse) {
                        return null;
                    }
                    /**把json字符串转换成json对象**/
                    jsonResult = JSONObject.fromObject(str);
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
 
 
    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static JSONObject httpGet(String url){
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            url = URLDecoder.decode(url, "UTF-8");
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
 
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity(),"UTF-8");
                /**把json字符串转换成json对象**/
                jsonResult = JSONObject.fromObject(strResult);
                
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }
	
	/**
	 * 
	 * <p>
	 * 功能
	 * </p>
	 * @author yueyong 时间 2014-5-22 下午4:15:54
	 * @param requestUrl
	 * @return
	 */
    public static JSONObject httpRequest(String requestUrl) {
        JSONObject jsonObject = null;
        StringBuffer buffer = null;
        try {
            // ��������
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");

            // ��ȡ������
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // ��ȡ���ؽ��
            buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

            // �ͷ���Դ
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            httpUrlConn.disconnect();
            
            System.out.println(">>>>>>>>>>> buffer "+buffer.length());
            if(buffer!=null)
                jsonObject = JSONObject.fromObject(buffer.substring(1, buffer.length()-1));
        } catch (Exception e) {
           System.out.println("��ѯwebservice����:"+e.getMessage());
        }
        return jsonObject;
    }
	// ��ȡaccess_token�Ľӿڵ�ַ��GET�� ��200����/�죩
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 
	 * <p>
	 * 功能
	 * </p>
	 * @author yueyong 时间 2014-5-22 下午4:15:58
	 * @param appid
	 * @param appsecret
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) {
	    if(accessToken!=null&&accessToken.getCreateDate().getTime()/1000+accessToken.getExpiresIn()<new Date().getTime()/1000){
	        return accessToken;
	    }
	    else{
	    AccessToken accessToken = null;
    	    String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
    	    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
    	    // �������ɹ�
    	    if (null != jsonObject) {
    	        try {
    	            accessToken = new AccessToken();
    	            accessToken.setToken(jsonObject.getString("access_token"));
    	            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
    	            accessToken.setCreateDate(new Date());
    	        } catch (JSONException e) {
    	            accessToken = null;
    	            log.error(" errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
    	        }
    	    }
    	    return accessToken;
    	    
	   }
	}
	
	// �˵�������POST�� ��100����/�죩
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	
	/**
	 * 
	 * <p>
	 * 功能
	 * </p>
	 * @author yueyong 时间 2014-5-22 下午4:16:06
	 * @param createTime
	 * @return
	 */
	public static String formatTime(String createTime) {
	    // ��΢�Ŵ����CreateTimeת����long���ͣ��ٳ���1000
	    long msgCreateTime = Long.parseLong(createTime) * 1000L;
	    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return format.format(new Date(msgCreateTime));
	}
    

    
    public static String getAppId(){
    
        return appId;
    }

    
    public static void setAppId(String appId){
    
        WeixinUtil.appId = appId;
    }

    
    public static String getAppSecret(){
    
        return appSecret;
    }

    
    public static void setAppSecret(String appSecret){
    
        WeixinUtil.appSecret = appSecret;
    }
    public final static String access_menu_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
   
    
    public static String code2tokenurl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    
    /**
     * 
     * <p>
     * 功能 code转微信openid
     * </p>
     * @author yueyong 时间 2014-5-22 下午3:20:01
     * @param code
     * @return
     */
    public static String code2openid(String code){
    	System.out.println("时间："+new Date()+"请求微信API获取用户openid");
        String requestUrl = code2tokenurl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
//        System.out.println(requestUrl);
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
//        System.out.println(jsonObject.toString());
        if(jsonObject!=null)
        	if (!StringUtils.contains(jsonObject.toString(), "40029")) {
        		return jsonObject.getString("openid");
        	}
        return null;
    }
    
    public static String jsapi_ticket_url="http://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=ACCESS_TOKEN";
    
    public static String getJsapiTicket(String accessToken){
    	String jsapi_ticket = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken);
    	JSONObject jsonObject = httpRequest(jsapi_ticket, "GET", null); 
    	if(jsonObject!=null)
        	if (!StringUtils.contains(jsonObject.toString(), "40029")) {
        		return jsonObject.getString("ticket");
        	}
        return null;
    }
    
    public static String longToShortUrl = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
    
    public static String getShorUrl(String accessToken,String longUrl){
    	String longToShort = longToShortUrl.replace("ACCESS_TOKEN", accessToken);
    	StringBuilder sb = new StringBuilder();
		sb.append("{\"action\":\"long2short\",");
		sb.append("\"long_url\":\""+longUrl+"}\"");
		JSONObject jsonObject=httpRequest(longToShort, "POST",sb.toString());
		if(jsonObject!=null){
//			System.out.println(jsonObject.toString());
			if(jsonObject.get("errmsg").equals("ok")){
				return jsonObject.getString("short_url");
			}
		}
		return null;
    }
    
    public static String userInfoTokenUrl= "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    
    public static UserInfoUtil getUserInfoByCode(String code){
    	 String requestUrl = code2tokenurl.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
//         System.out.println(requestUrl);
         JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
//         System.out.println(jsonObject.toString());
         if(jsonObject!=null)
         	if (!StringUtils.contains(jsonObject.toString(), "40029")) {
         		requestUrl = userInfoTokenUrl.replace("ACCESS_TOKEN", jsonObject.getString("access_token")).
         				replace("OPENID", jsonObject.getString("openid"));
         		jsonObject = httpRequest(requestUrl, "GET", null);
         		if(jsonObject!=null){
         			if(!StringUtils.contains(jsonObject.toString(), "errcode")){
         				UserInfoUtil userInfoUtil = new UserInfoUtil();
         				userInfoUtil.setOpenid(jsonObject.getString("openid"));
         				userInfoUtil.setNickname(jsonObject.getString("nickname"));
         				userInfoUtil.setProvince(jsonObject.getString("province"));
         				userInfoUtil.setCity(jsonObject.getString("city"));
         				userInfoUtil.setCountry(jsonObject.getString("country"));
         				userInfoUtil.setHeadimgurl(jsonObject.getString("headimgurl"));
         				userInfoUtil.setUnionid(jsonObject.getString("unionid"));
         				return userInfoUtil;
         			}
         		}
         	}
    	return null;
    }
    
    public static void main(String[] args) {
    	String requestUrl ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1967ca0ef7d225c0&redirect_uri=http://css.starsgrassland.com/user/userAction!jampshopIndex.action&response_type=code&scope=snsapi_userinfo&state=asd#wechat_redirect";
    	getShorUrl(getAccessToken(appId, appSecret).getToken(), requestUrl);
    	
    }
}