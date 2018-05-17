package com.test;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.google.util.ERCodeUtil;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.service.impl.UserServiceImpl;
import com.zklc.weishangcheng.member.service.impl.WeixinAutosendmsgServiceImpl;
import com.zklc.weixin.util.UserInfoUtil;

import net.sf.json.JSONObject;

@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestQrCodeImage extends AbstractJUnit4SpringContextTests{

	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	public static void main(String[] args) throws Exception {
		WeixinAutosendmsgServiceImpl autosendmsgService = new WeixinAutosendmsgServiceImpl();
		UserServiceImpl service = new UserServiceImpl();
		String wxOpenid = "oyZgewZFscVWHzw7ZJAIO3wTbwAQ";
		UserInfoUtil userInfo = autosendmsgService.processUserInfoObject(wxOpenid);
		JSONObject jsonObject = autosendmsgService.processTicketByToken();
		String modelPath = "C:/Users/Administrator/Desktop/";
		String userQrCode = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date())+".png";
		//chaoguo/src/main/webapp/images/qrcodeImgs
		String userImgPathOld = modelPath+userQrCode;
		BufferedImage d = service.loadImageLocal(modelPath+"blank.png");//modelPath
		BufferedImage b = service.loadImageUrl(userInfo.getHeadimgurl());//headImg
		service.modifyImage(d,userInfo.getNickname(),200,90);
		service.modifyImagetogeter(b, d, 100,100, 30,50);
		System.out.println(jsonObject.getString("url"));
		BufferedImage e= ERCodeUtil.createImage(jsonObject.getString("url"), userInfo.getHeadimgurl(),true);
		service.writeImageLocal(userImgPathOld, service.modifyImagetogeter(e, d,386,386, 167,670));
	}

	@Test
	public void testQrCode() throws Exception{
//		JifenUserServiceImpl service = new JifenUserServiceImpl();
		String wxOpenid = "osr0JszIptV-QCHYQ58aD7Loq3Yw";
//		UserInfoUtil userInfo = autosendmsgService.processUserInfoObject(wxOpenid);
		JSONObject jsonObject = autosendmsgService.processTicketByToken();
//		String modelPath = "C:/Users/Administrator/Desktop/";
//		String userQrCode = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date())+".png";
//		//chaoguo/src/main/webapp/images/qrcodeImgs
//		String userImgPathOld = modelPath+userQrCode;
//		BufferedImage d = service.loadImageLocal(modelPath+"blank.png");//modelPath
//		BufferedImage b = service.loadImageUrl(userInfo.getHeadimgurl());//headImg
//		service.modifyImage(d,userInfo.getNickname(),200,90);
//		service.modifyImagetogeter(b, d, 100,100, 30,50);
		System.out.println(jsonObject.getString("url"));
//		BufferedImage e= ERCodeUtil.createImage(jsonObject.getString("url"), userInfo.getHeadimgurl(),true);
//		service.writeImageLocal(userImgPathOld, service.modifyImagetogeter(e, d,386,386, 167,670));
	}
}
