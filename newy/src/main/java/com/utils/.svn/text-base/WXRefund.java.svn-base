package com.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

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
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.zklc.weixin.util.SystemMessage;

public class WXRefund {
	public static final String MCH_ID = SystemMessage.getString("MCH_ID");      //商户号
	public static final String WXAPPID = SystemMessage.getString("APPID");     //公众账号appid
	public static final String KEY = SystemMessage.getString("PARTNERKEY");         //秘钥
	public static final String IP ="101.201.74.48";       
	public static final String URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	

	public static String refund(String openId, String orderNO, String amount) {
		System.out.println("取消订单请求微信退款");
		String responseXML="";
		String nonce_str = getRandomNum(12);
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("mch_appid", WXAPPID);
		packageParams.put("mchid", MCH_ID);
		//packageParams.put("device_info", "");
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("partner_trade_no", orderNO);// 商户订单号，需保持唯一性
		packageParams.put("openid", openId);// 商户appid下，某用户的openid
		packageParams.put("check_name", "NO_CHECK");
		// NO_CHECK：不校验真实姓名
		// FORCE_CHECK：强校验真实姓名（未实名认证的用户会校验失败，无法转账）
		// OPTION_CHECK：针对已实名认证的用户才校验真实姓名（未实名认证用户不校验，可以转账成功）
		//packageParams.put("re_user_name", "");
		packageParams.put("amount",amount);
		packageParams.put("desc", "订单退款");// 企业付款操作说明信息。必填。
		packageParams.put("spbill_create_ip", IP);// 调用接口的机器Ip地址
		sign(packageParams);
		String xml = getRequestXml(packageParams);
		try {
			String path = "/yunwei8/caoyuan.p12";
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				path = "c:/yifei.p12";
			}
			FileInputStream instream = new FileInputStream(new File(path));
			 responseXML = post(xml, instream);
			System.out.println("微信退款返回结果：" + responseXML);
			
		} catch (Exception e) {
			System.out.println("微信退款返回结果error：" + e.toString());
			e.printStackTrace();
		}
		return responseXML;
	}
	
	public static String post(String requestXML, FileInputStream instream) throws NoSuchAlgorithmException,
			CertificateException, IOException, KeyManagementException, UnrecoverableKeyException, KeyStoreException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		try {
			keyStore.load(instream, MCH_ID.toCharArray());
		} finally {
			instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, MCH_ID.toCharArray()).build();
		/*
		 * SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		 * sslcontext, new String[] { "TLSv1" }, null,
		 * SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		 * CloseableHttpClient httpclient =
		 * HttpClients.custom().setSSLSocketFactory(sslsf).build();
		 */

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		String result = "";
		try {
			HttpPost httpPost = new HttpPost(URL);
			StringEntity reqEntity = new StringEntity(requestXML, "UTF-8"); // 如果此处编码不对，可能导致客户端签名跟微信的签名不一致
			reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(entity.getContent(), "UTF-8"));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						result += text;
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
                     sb.append("<"+k+">"+v+"</"+k+">");
                 }
                sb.append("</xml>");
                return sb.toString();
        }
	
	public static Map doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}
		Map m = new HashMap();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}
		// 关闭流
		in.close();

		return m;
	}
	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
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
}
