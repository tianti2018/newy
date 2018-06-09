package com.tw.web.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * @author userwyh
 * @date 2015年3月5日 
 * @time 上午11:53:40 
 * @version 1.0
 */
public class HongBaoUtil {
	public static final String MCH_ID = SystemMessage.getString("MCH_ID");      //商户号
	public static final String WXAPPID = SystemMessage.getString("APPID");     //公众账号appid
	public static final String NICK_NAME = "山人物语";   //提供方名称
	public static final String SEND_NAME = "山人物语";   //商户名称
	public static final int MIN_VALUE = 1000;       //红包最小金额 单位:分
	public static final int MAX_VALUE = 5000;       //红包最大金额 单位:分
	public static final int TOTAL_NUM = 1;         //红包发放人数
	public static final String WISHING = "山人物语提现红包";     //红包祝福语
	public static final String CLIENT_IP = "192.168.2.117";   //调用接口的机器IP
	public static final String ACT_NAME = "山人物语提现";    //活动名称
	public static final String REMARK = "山人物语提现";      //备注
	public static final String KEY = SystemMessage.getString("PARTNERKEY");         //秘钥
	
	public static final int FAIL = 0;              //领取失败
	public static final int SUCCESS = 1;           //领取成功
	public static final int LOCK = 2;              //已在余额表中锁定该用户的余额,防止领取的红包金额大于预算
	
	/**
	 * 对请求参数名ASCII码从小到大排序后签名
	 * @param params
	 */
	public static void sign(SortedMap<String, String> params){
		Set<Entry<String,String>> entrys=params.entrySet();  
		Iterator<Entry<String,String>> it=entrys.iterator();  
		StringBuffer result = new StringBuffer();
		while(it.hasNext())  
		{  
		   Entry<String,String> entry=it.next();  
		   result.append(entry.getKey())
		         .append("=")
		         .append(entry.getValue())
		         .append("&");
		}  
		result.append("key=").append(KEY);
		params.put("sign", MD5Util.MD5Encode(result.toString(),"utf-8"));
	}
	/**
	 * 生成提交给微信服务器的xml格式参数
	 * @param params
	 * @return
	 */
	public static String getRequestXml(SortedMap<String,String> params){
        	StringBuffer sb = new StringBuffer();
                sb.append("<xml>");
                Set es = params.entrySet();
                Iterator it = es.iterator();
                while(it.hasNext()) {
                     Map.Entry entry = (Map.Entry)it.next();
                     String k = (String)entry.getKey();
                     String v = (String)entry.getValue();
                     if ("nick_name".equalsIgnoreCase(k)||"send_name".equalsIgnoreCase(k)||"wishing".equalsIgnoreCase(k)||"act_name".equalsIgnoreCase(k)||"remark".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
                         sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
                      }else {
                         sb.append("<"+k+">"+v+"</"+k+">");
                      }
                 }
                sb.append("</xml>");
                return sb.toString();
        }
	/**
	 * 创建map
	 * @param billNo
	 * @param openid
	 * @param userId
	 * @param amount
	 * @return
	 */
	public static SortedMap<String, String> createMap(String billNo,String openid,String userId,int amount){
		SortedMap<String, String> params = new TreeMap<String, String>();
		params.put("wxappid",WXAPPID);
		params.put("nonce_str",createNonceStr());
		params.put("mch_billno",billNo);
		params.put("mch_id", MCH_ID);
		params.put("nick_name", NICK_NAME);
		params.put("send_name", SEND_NAME);
		params.put("re_openid", openid);
		params.put("total_amount", amount+"");
		params.put("min_value", amount+"");
		params.put("max_value", amount+"");
		params.put("total_num", TOTAL_NUM+"");
		params.put("wishing", WISHING);
		params.put("client_ip", CLIENT_IP);
		params.put("act_name", ACT_NAME);
		params.put("remark", REMARK);
		return params;
	}
	
	/**
	 * 创建map
	 * @param 商户订单号  mch_billno
	 * @param openid
	 * @param userId
	 * @param amount
	 * @return
	 */
	public static SortedMap<String, String> createMapSelect(String mch_billno){
		SortedMap<String, String> params = new TreeMap<String, String>();
		params.put("appid",WXAPPID);
		params.put("nonce_str",createNonceStr());
		params.put("mch_billno", mch_billno);
		params.put("mch_id", MCH_ID);
		params.put("bill_type", "MCHT");
		return params;
	}
	
	
	/**
	 * 生成随机字符串
	 * @return
	 */
	public static String createNonceStr() {
               return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }
	/**
	 * 生成商户订单号
	 * @param mch_id  商户号
	 * @param userId  该用户的userID
	 * @return
	 */
	public static String createBillNo(String userId){
		//组成： mch_id+yyyymmdd+10位一天内不能重复的数字
		//10位一天内不能重复的数字实现方法如下:
		//因为每个用户绑定了userId,他们的userId不同,加上随机生成的(10-length(userId))可保证这10位数字不一样
		Date dt=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		String nowTime= df.format(dt);
		int length = 10 - userId.length();
		return MCH_ID + nowTime + userId + getRandomNum(length);
	}
	
	public static String createBillNo() {
		Date dt=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		String nowTime= df.format(dt);
		return MCH_ID + nowTime + getRandomNum(10);
	}
	/**
	 * 生成特定位数的随机数字
	 * @param length
	 * @return
	 */
	private static String getRandomNum(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	}
        /**
	 * post提交到微信服务器
	 * @param requestXML
	 * @param instream  
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 * @throws KeyStoreException
	 */
	public static String post(String requestXML,FileInputStream instream) throws NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException{
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        try {
            keyStore.load(instream, MCH_ID.toCharArray());
        }  finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, MCH_ID.toCharArray()).build();
       /* SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();*/
        
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        
        String result = "";
        try {
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");
            StringEntity  reqEntity  = new StringEntity(requestXML,"UTF-8"); //如果此处编码不对，可能导致客户端签名跟微信的签名不一致
            reqEntity.setContentType("application/x-www-form-urlencoded"); 
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                    	result +=text;
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return result;
	}
	
	
	/**
	 * 现金红包查询
	 * @param requestXML
	 * @param instream
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws CertificateException
	 * @throws IOException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 * @throws KeyStoreException
	 */
	public static String postslect(String requestXML,FileInputStream instream) throws NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException{
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        try {
            keyStore.load(instream, MCH_ID.toCharArray());
        }  finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, MCH_ID.toCharArray()).build();
       /* SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();*/
        
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        
        String result = "";
        try {
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo");
            StringEntity  reqEntity  = new StringEntity(requestXML,"UTF-8"); //如果此处编码不对，可能导致客户端签名跟微信的签名不一致
            reqEntity.setContentType("application/x-www-form-urlencoded"); 
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                    	result +=text;
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return result;
	}
	
	
	/**
	 * 
	 * 
	 * 返回参数 
			字段名 变量名 必填 示例值 类型 说明 
			返回状态码  return_code 是  SUCCESS String(16) SUCCESS/FAIL
			此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断  
			返回信息  return_msg 否  签名失败  String(128) 返回信息，如非空，为错误原因 
			签名失败 
			参数格式校验错误  
			
			以下字段在return_code为SUCCESS的时候有返回
			
			字段名 变量名 必填 示例值 类型 说明 
			签名  sign 是  C380BEC2BFD727A4B6845133519F3AD6 String(32) 详见签名生成算法 
			业务结果  result_code 是  SUCCESS String(16) SUCCESS/FAIL 
			错误代码  err_code 否  SYSTEMERROR String(32) 错误码信息  
			错误代码描述  err_code_des 否  系统错误 String(128) 结果信息描述  
			
			以下字段在return_code 和result_code都为SUCCESS的时候有返回
			
			字段名 变量名 必填 示例值 类型 描述 
			商户订单号  mch_billno  是  10000098201411111234567890 String(28) 商户使用查询API填写的商户单号的原路返回  
			商户号  mch_id  是  10000098  String(32) 微信支付分配的商户号  
			红包单号  detail_id  是  1000000000201503283103439304  String(32) 使用API发放现金红包时返回的红包单号  
			红包状态  status 是  RECEIVED string(16) SENDING:发放中 
			SENT:已发放待领取 
			FAILED：发放失败 
			RECEIVED:已领取 
			REFUND:已退款  
			发放类型  send_type 是  API String(32)  API:通过API接口发放 
			UPLOAD:通过上传文件方式发放 
			ACTIVITY:通过活动方式发放  
			红包类型  hb_type  是  GROUP String(32) GROUP:裂变红包 
			NORMAL:普通红包  
			红包个数  Total_num 是  1 int 红包个数  
			红包金额  Total_amount 是  5000 int 红包总金额（单位分）  
			失败原因  reason 否  余额不足  String(32) 发送失败原因  
			红包发送时间  Send_time 是  2015-04-21 20:00:00  String(32)   
			红包退款时间  Refund_time 否  2015-04-21 23:03:00 String(32) 红包的退款时间（如果其未领取的退款）  
			红包退款金额  Refund_amount 否  8000 Int 红包退款金额  
			祝福语  wishing 否  新年快乐  String(128) 祝福语  
			活动描述  Remark 否  新年红包  String(256) 活动描述，低版本微信可见  
			活动名称  Act_name 否  新年红包  String(32) 发红包的活动名称  
			裂变红包领取列表  hblist 否  内容如下表    裂变红包的领取列表  
			领取红包的Openid openid 是  ohO4GtzOAAYMp2yapORH3dQB3W18  String(32) 领取红包的openid 
			金额  amount 是  100 int 领取金额  
			接收时间  Rcv_time 是  2015-04-21 20:00:00  String(32) 领取红包的时间  

	 * @param responseXML
	 * @return
	 */
	public static Map<String,String> changeXml(String responseXML) {
		try {
			Document document;
			document = DocumentHelper.parseText(responseXML);
			
			 Element root = document.getRootElement();
			 List<Element> elements = root.elements();
			 Map<String,String> map = new HashMap<String, String>();
			 for (int i = 0;i<elements.size();i++) {
				 map.put(elements.get(i).getName(), elements.get(i).getText());
			 }
			 return map;
		} 
		catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
	