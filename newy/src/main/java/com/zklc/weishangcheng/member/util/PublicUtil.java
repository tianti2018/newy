package com.zklc.weishangcheng.member.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.sf.json.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weixin.messageSend.SendText;
import com.zklc.weixin.messageSend.text;
import com.zklc.weixin.util.DateUtil;
import com.zklc.weixin.util.MessageUtil;
import com.zklc.weixin.util.WeixinUtil;

/**
 * 
 * <p>
 * 功能 常量类
 * </p>
 * <p>
 * Copyright 北京中科联诚软件有限公司 2014 All right reserved.
 * </p>
 * 
 * @author yueyong 时间 2014-3-14 上午9:18:04
 * @version 1.0 </br> 最后修改人 无
 */
public class PublicUtil {
	/**
	 * 用户敏感操作安全码(每次输入一小时内有效，key为登录名，value为时间)
	 */
	public final static Map SecurityCodeMap = new HashMap();

	/**
	 * 判断用户是否有权限进行敏感操作
	 * 
	 * @return
	 */
	public static boolean isRight(JifenUser user) {
		boolean returnObj = false;
		// 判断1小时内是否输入过二级密码，如输入过则直接进入，如没有输入则进入输入二级密码页
		if (PublicUtil.SecurityCodeMap.containsKey(user.getLoginName())) {
			Date putDate = (Date) PublicUtil.SecurityCodeMap.get(user
					.getLoginName());
			if (DateUtil.nHoursBetweenTwoDate(new Date(), putDate) <= 1) {
				returnObj = true;
			}
		}
		return returnObj;
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getSixRandom() {
		Random rad = new Random();
		String randS = "";
		for (int i = 0; i < 6; i++) {
			randS += rad.nextInt(10);
		}
		return randS;
	}

	/**
	 * 生成订单号(订单编号规则 日期+随机六位数)
	 * 
	 * @return
	 */
	public static String getOrderNo() {
		String returnStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmSS");
		String fd = sdf.format(new Date());
		returnStr = fd + getSixRandom();
		return returnStr;
	}

	/**
	 * 向微信用户发送消息
	 * 
	 * @param openid
	 *            用户微信标识符
	 * @param content
	 *            消息内容
	 */
	public static void sendMsgOne(String openid, String content) {
		if (openid != null) {
			SendText sendText = new SendText();
			sendText.setTouser(openid);
			sendText.setMsgtype(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
			text text = new text();
			text.setContent(content);
			sendText.setText(text);
			JSONObject jObject = JSONObject.fromObject(sendText);
			String token = WeixinUtil.getAccessToken(WeixinUtil.appId,
					WeixinUtil.appSecret).getToken();
			String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
					+ token;
			HttpPost pmethod = new HttpPost(url);
			StringEntity entity = null;
			entity = new StringEntity(jObject.toString(), Consts.UTF_8);
			entity.setContentType("application/json");
			pmethod.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			try {
				HttpResponse res = client.execute(pmethod);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
