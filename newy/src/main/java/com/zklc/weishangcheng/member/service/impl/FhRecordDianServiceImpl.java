package com.zklc.weishangcheng.member.service.impl;

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

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.FhRecordDianDao;
import com.zklc.weishangcheng.member.hibernate.persistent.DianForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecordDian;
import com.zklc.weishangcheng.member.hibernate.persistent.HongbaoDian;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderDian;
import com.zklc.weishangcheng.member.service.DianForUserService;
import com.zklc.weishangcheng.member.service.FhRecordDianService;
import com.zklc.weishangcheng.member.service.HongbaoDianService;
import com.zklc.weishangcheng.member.service.UsersService;
import com.zklc.weishangcheng.member.service.OrderDianService;
import com.zklc.weishangcheng.member.util.HongBaoUtil;

@Service
public class FhRecordDianServiceImpl extends BaseServiceImp<FhRecordDian, Integer> implements
		FhRecordDianService {
	@Autowired
	private FhRecordDianDao fhRecordDao;
	@Autowired
	private OrderDianService orderDianService;
	@Autowired
	private DianForUserService dianForUserService;
	@Autowired
	private HongbaoDianService hongbaoDianService;
	@Autowired
	private UsersService userService;

	@Override
	public FhRecordDian findFhRecordBytoUserId(Integer puserId,
			Integer fromUserId, double amount, Integer flag, Integer ordersId, int level) {
		return fhRecordDao.findFhRecordBytoUserId(puserId,fromUserId,amount,flag,ordersId,level);
	}

	@Override
	public int findCountFhRecordByLevel(Integer userId, int level) {
		
		return fhRecordDao.findCountFhRecordByLevel(userId,level);
	}

	@Override
	public String fahongbao(String wxOpenId, Integer userId, Integer fromUserId,
			Integer level, Integer amount, Integer flagCount) {
		System.out.println("进入发送店主红包方法---------------------------------");
		
		String message = "";
		
		//更新状态
		FhRecordDian fhRecord = fhRecordDao.findFhRecordBytoUserId(userId,fromUserId,amount*1.0,1,null,level);
		if (null!=fhRecord) {
			
			DianForUser dianForUser = dianForUserService.findByUserId(fromUserId);
			if(dianForUser==null){
				dianForUser = new DianForUser();
				dianForUser.setUserId(fromUserId);
				dianForUserService.save(dianForUser);
			}
			boolean faHongbao=true;
			Integer levelAndNum=0;
			switch (level) {
			case 1:
				if(dianForUser.getDian1()!=null){
					faHongbao =false;
				}else{
					levelAndNum=11;
				}
				break;
			case 2:
				if(dianForUser.getDian2()!=null){
					faHongbao =false;
				}else{
					levelAndNum=21;
				}
				break;
			case 3:
				if(amount.equals(200)){
					if(dianForUser.getDian31()!=null){
						faHongbao =false;
					}else{
						levelAndNum=31;
					}
				}else {
					if(dianForUser.getDian32()!=null){
						faHongbao =false;
					}else{
						levelAndNum=32;
					}
				}
				break;
			case 4:
				if(amount.equals(200)){
					if(dianForUser.getDian41()!=null){
						faHongbao =false;
					}else{
						levelAndNum=41;
					}
				}else {
					if(dianForUser.getDian42()!=null){
						faHongbao =false;
					}else{
						levelAndNum=42;
					}
				}
				break;
			case 5:
				if(dianForUser.getDian5()!=null){
					faHongbao =false;
				}else{
					levelAndNum=51;
				}
				break;
			case 6:
				if(dianForUser.getDian6()!=null){
					faHongbao =false;
				}else{
					levelAndNum=61;
				}
				break;
			case 7:
				if(dianForUser.getDian7()!=null){
					faHongbao =false;
				}else{
					levelAndNum=71;
				}
				break;
			case 8:
				if(dianForUser.getDian8()!=null){
					faHongbao =false;
				}else{
					levelAndNum=81;
				}
				break;
			case 9:
				if(amount.equals(200)){
					if(dianForUser.getDian91()!=null){
						faHongbao =false;
					}else{
						levelAndNum=91;
					}
				}else {
					if(dianForUser.getDian92()!=null){
						faHongbao =false;
					}else{
						levelAndNum=92;
					}
				}
				break;
			}
			if(faHongbao){
			//发送红包
				message =fahongbaozhu(wxOpenId,fhRecord,amount*100,levelAndNum);
				System.out.println(message);
				if ("发放成功".equals(message)) {
					switch (levelAndNum) {
					case 11:dianForUser.setDian1(1);
						break;
					case 21:dianForUser.setDian2(1);
						break;
					case 31:dianForUser.setDian31(1);
						break;
					case 32:dianForUser.setDian32(1);
						break;
					case 41:dianForUser.setDian41(1);
						break;
					case 42:dianForUser.setDian42(1);
						break;
					case 51:dianForUser.setDian5(1);
						break;
					case 61:dianForUser.setDian6(1);
						break;
					case 71:dianForUser.setDian7(1);
						break;
					case 81:dianForUser.setDian8(1);
						break;
					case 91:dianForUser.setDian91(1);
						break;	
					case 92:dianForUser.setDian92(1);
						break;
					}
					fhRecord.setFlag(2);
					fhRecord.setUpdaDate(new Date());
					fhRecordDao.update(fhRecord);
					dianForUserService.update(dianForUser);
				}
			}else {
				message = "您正在进行重复提交，请等待服务器的反应...";
			}
		}
		else {
			message = "您正在进行重复提交，请等待服务器的反应...";
		}
		return message;
	
	}
	
	@Override
	public String bufahongbao(String wxOpenId, Integer userId, Integer fromUserId,
			Integer level, Integer amount, Integer flagCount) {
		System.out.println("进入发送店主红包方法---------------------------------");
		
		String message = "";
		
		//更新状态
		FhRecordDian fhRecord = fhRecordDao.findFhRecordBytoUserId(userId,fromUserId,amount*1.0,4,null,level);
		if (null!=fhRecord) {
			
			DianForUser dianForUser = dianForUserService.findByUserId(fromUserId);
			if(dianForUser==null){
				dianForUser = new DianForUser();
				dianForUser.setUserId(fromUserId);
				dianForUserService.save(dianForUser);
			}
			boolean faHongbao=true;
			Integer levelAndNum=0;
			switch (level) {
			case 1:
				if(dianForUser.getDian1()!=null){
					faHongbao =false;
				}else{
					levelAndNum=11;
				}
				break;
			case 2:
				if(dianForUser.getDian2()!=null){
					faHongbao =false;
				}else{
					levelAndNum=21;
				}
				break;
			case 3:
				if(amount.equals(200)){
					if(dianForUser.getDian31()!=null){
						faHongbao =false;
					}else{
						levelAndNum=31;
					}
				}else {
					if(dianForUser.getDian32()!=null){
						faHongbao =false;
					}else{
						levelAndNum=32;
					}
				}
				break;
			case 4:
				if(amount.equals(200)){
					if(dianForUser.getDian41()!=null){
						faHongbao =false;
					}else{
						levelAndNum=41;
					}
				}else {
					if(dianForUser.getDian42()!=null){
						faHongbao =false;
					}else{
						levelAndNum=42;
					}
				}
				break;
			case 5:
				if(dianForUser.getDian5()!=null){
					faHongbao =false;
				}else{
					levelAndNum=51;
				}
				break;
			case 6:
				if(dianForUser.getDian6()!=null){
					faHongbao =false;
				}else{
					levelAndNum=61;
				}
				break;
			case 7:
				if(dianForUser.getDian7()!=null){
					faHongbao =false;
				}else{
					levelAndNum=71;
				}
				break;
			case 8:
				if(dianForUser.getDian8()!=null){
					faHongbao =false;
				}else{
					levelAndNum=81;
				}
				break;
			case 9:
				if(amount.equals(200)){
					if(dianForUser.getDian91()!=null){
						faHongbao =false;
					}else{
						levelAndNum=91;
					}
				}else {
					if(dianForUser.getDian92()!=null){
						faHongbao =false;
					}else{
						levelAndNum=92;
					}
				}
				break;
			}
			if(faHongbao){
			//发送红包
				message =fahongbaozhu(wxOpenId,fhRecord,amount*100,levelAndNum);
				System.out.println(message);
				if ("发放成功".equals(message)) {
					switch (levelAndNum) {
					case 11:dianForUser.setDian1(1);
						break;
					case 21:dianForUser.setDian2(1);
						break;
					case 31:dianForUser.setDian31(1);
						break;
					case 32:dianForUser.setDian32(1);
						break;
					case 41:dianForUser.setDian41(1);
						break;
					case 42:dianForUser.setDian42(1);
						break;
					case 51:dianForUser.setDian5(1);
						break;
					case 61:dianForUser.setDian6(1);
						break;
					case 71:dianForUser.setDian7(1);
						break;
					case 81:dianForUser.setDian8(1);
						break;
					case 91:dianForUser.setDian91(1);
						break;	
					case 92:dianForUser.setDian92(1);
						break;
					}
					fhRecord.setFlag(2);
					fhRecord.setUpdaDate(new Date());
					fhRecordDao.update(fhRecord);
					dianForUserService.update(dianForUser);
				}
			}else {
				message = "您正在进行重复提交，请等待服务器的反应...";
			}
		}
		else {
			message = "您正在进行重复提交，请等待服务器的反应...";
		}
		return message;
	
	}
	
	public String fahongbaozhu(String wxOpenId,FhRecordDian fhRecord,Integer amount,Integer levelAndNum) {
		String message="";
		String billNo = HongBaoUtil.createBillNo();
		SortedMap<String, String> map = HongBaoUtil.createMap(billNo, wxOpenId, null,amount);  
		HongBaoUtil.sign(map);  
		String requestXML = HongBaoUtil.getRequestXml(map);  
		try {
			String path="/yunwei8/caoyuan.p12";
			if(System.getProperty("os.name").toLowerCase().contains("windows")) {
				path="c:/yifei.p12";
			}
			 FileInputStream instream = new FileInputStream(new File(path));
			 String responseXML = HongBaoUtil.post(requestXML,instream);
			 
			 Document document;
			 document = DocumentHelper.parseText(responseXML);
			 Element root = document.getRootElement();
			 List<Element> elements = root.elements();
			 Element element = elements.get(0);
			 Element element2 = elements.get(1);
			 message=element2.getTextTrim();
			 
			 HongbaoDian hongbao = new HongbaoDian();
			 hongbao.setFhrecordId(fhRecord.getFhId());
			 hongbao.setAddTime(new Date());
			 hongbao.setOpenid(wxOpenId);
			 hongbao.setAmount(amount/100);
			 hongbao.setBillNo(billNo);
			 hongbao.setRemark(responseXML);
			 hongbao.setUserId(fhRecord.getUserId());
			 hongbao.setFromUserId(fhRecord.getFromUserId());
			 hongbao.setFhrecordId(fhRecord.getFhId());
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
					 hongbao.setWxBillNo(send_listid);
				 }
			 }
			 if (send_listid!="") {
				 hongbao.setResult(1);
				 message="发放成功";
			 }else if(responseXML.contains("请求已受理")){
				 hongbao.setResult(1);
				 message="发放成功";
			 }else{
				hongbao.setResult(0);
			 }
			 hongbaoDianService.save(hongbao);
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
		} 
		catch (DocumentException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	public static String createDianHBBillNo(String ordersBH,Integer levelAndNum){
		//前边是订单编号,中间是对应等级和次数,最后是发放次数
		return ordersBH+"_"+levelAndNum+"_"+0;
	}

	@Override
	public Double findTotalJiFenOneByUserId(Integer userId, String string,
			String flag) {

		StringBuffer hql = new StringBuffer("select sum(t.fhmoney) from fhrecord_dian t where 1=1 ");
		if(userId!=null){
			hql.append(" and t.userId="+userId);
			if (StringUtils.isNotEmpty(flag)) {
				hql.append(" and t.flag in("+flag+")");	
			}
			List<Double> money =super.findBySql(hql.toString(), null);
			if (null!=money) {
				return null==money.get(0)?0:money.get(0);
			}
		}
		return 0.0;
	
	}

}
