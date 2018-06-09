package com.tw.web.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.HongbaoyDao;
import com.tw.web.dao.ShouYiForUserDao;
import com.tw.web.dao.UseryDao;
import com.tw.web.hibernate.persistent.Hongbaoy;
import com.tw.web.hibernate.persistent.ShouYiForUser;
import com.tw.web.hibernate.persistent.Usery;
import com.tw.web.util.CommUtils;
import com.tw.web.util.HongBaoUtil;
import com.tw.web.util.WeiXinFuKuanUtil;

@Repository
public class ShouYiForUserDaoImpl extends CRUDBaseHibernateDAOImpl implements ShouYiForUserDao {

	@Override
	protected Class getPojoClass() {
		return ShouYiForUser.class;
	}

	@Autowired
	private UseryDao useryDao;
	@Autowired
	private HongbaoyDao hongbaoyDao;
	@Override
	public Hongbaoy tongguo(ShouYiForUser shouYiForUser) {
		if(shouYiForUser!=null){
			Usery usery = null;
			List<Usery> userys = useryDao.findEntityByPropertiName("dianPuId", shouYiForUser.getDianpuId());
			if(userys!=null&&userys.size()>0){
				usery = userys.get(0);
			}
			if(usery!=null){
				Integer amount = CommUtils.getInt(shouYiForUser.getShouyi());
				if(amount<0)
					amount = 0-amount;
				
				String billNo = HongBaoUtil.createBillNo();
				shouYiForUser.setBeizhu("提现审核通过");
				shouYiForUser.setDakuanDan(billNo);
				shouYiForUser.setTixian(2);
				shouYiForUser.setPassDate(new Date());
				update(shouYiForUser);
				Hongbaoy hongbaoy = new Hongbaoy();
				hongbaoy.setAddTime(new Date());
				hongbaoy.setAmount(amount);
				hongbaoy.setBillNo(billNo);
				hongbaoy.setBufaNum(0);
				hongbaoy.setDianpuId(shouYiForUser.getDianpuId());
				hongbaoy.setOpenid(usery.getWxOpenid());
				hongbaoy.setShouyiId(shouYiForUser.getId());
				hongbaoy.setResult(1);
				hongbaoyDao.saveOrUpdate(hongbaoy);
				return hongbaoy;
			}
		}
		return null;
	}
	
	@Override
	public String fahongbao(Hongbaoy hongbaoy) {
		String message="";
		if(hongbaoy!=null){
		SortedMap<String, String> map = HongBaoUtil.createMap(hongbaoy.getBillNo(), hongbaoy.getOpenid(), null,hongbaoy.getAmount()*100);  
		HongBaoUtil.sign(map);  
		String requestXML = HongBaoUtil.getRequestXml(map);  
		try {
			String path="/shanrenwuyu.p12";
			if(System.getProperty("os.name").toLowerCase().contains("windows")) {
				path="c:/shanrenwuyu.p12";
			}
			 FileInputStream instream = new FileInputStream(new File(path));
			 String responseXML = HongBaoUtil.post(requestXML,instream);
			 
			 Document document = DocumentHelper.parseText(responseXML);
			 Element root = document.getRootElement();
			 List<Element> elements = root.elements();
			 String return_msg = "";
			 String return_code = "";
			 String result_code = "";
			 String send_listid = "";
			 for(Element el:elements){
				 if(el.getName().trim().equals("return_code")){
					 return_code = el.getTextTrim();
				 }
				 if(el.getName().trim().equals("return_msg")){
					 return_msg = el.getTextTrim();
					 message=return_msg;
				 }
				 if(el.getName().trim().equals("result_code")){
					 result_code = el.getTextTrim();
				 }
				 if(el.getName().trim().equals("send_listid")){
					 send_listid = el.getTextTrim();
				 }
			 }
			 if (send_listid!="") {
				 message="发放成功";
			 }else if(responseXML.contains("请求已受理")){
				 message="发放成功";
			 }
		} 
		catch (KeyManagementException e) {
			e.printStackTrace();
		} 
		catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (CertificateException e) {
			e.printStackTrace();
		} 
		catch (KeyStoreException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		}
		return message;
	}

	@Override
	public String weiXinFuKuan(Hongbaoy hongbaoy) {

		String message="";
		if(hongbaoy!=null){
		SortedMap<String, String> map = WeiXinFuKuanUtil.createMap(hongbaoy.getBillNo(), hongbaoy.getOpenid(), null,hongbaoy.getAmount()*100);  
		WeiXinFuKuanUtil.sign(map);  
		String requestXML = WeiXinFuKuanUtil.getRequestXml(map);  
		try {
			String path="/shanrenwuyu.p12";
			if(System.getProperty("os.name").toLowerCase().contains("windows")) {
				path="c:/shanrenwuyu.p12";
			}
			 FileInputStream instream = new FileInputStream(new File(path));
			 String responseXML = WeiXinFuKuanUtil.postFuQian(requestXML,instream);
			 
			 Document document = DocumentHelper.parseText(responseXML);
			 Element root = document.getRootElement();
			 List<Element> elements = root.elements();
			 String return_msg = "";
			 String return_code = "";
			 String result_code = "";
			 String send_listid = "";
			 for(Element el:elements){
				 if(el.getName().trim().equals("return_code")){
					 return_code = el.getTextTrim();
				 }
				 if(el.getName().trim().equals("return_msg")){
					 return_msg = el.getTextTrim();
					 message=return_msg;
				 }
				 if(el.getName().trim().equals("result_code")){
					 result_code = el.getTextTrim();
				 }
				 if(el.getName().trim().equals("send_listid")){
					 send_listid = el.getTextTrim();
				 }
			 }
			 if (send_listid!="") {
				 message="发放成功";
			 }else if(responseXML.contains("请求已受理")){
				 message="发放成功";
			 }
		} 
		catch (KeyManagementException e) {
			e.printStackTrace();
		} 
		catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		catch (CertificateException e) {
			e.printStackTrace();
		} 
		catch (KeyStoreException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} 
		}
		return message;
	
	}

}
