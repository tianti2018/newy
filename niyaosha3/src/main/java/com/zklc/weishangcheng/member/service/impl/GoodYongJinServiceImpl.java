package com.zklc.weishangcheng.member.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.GoodYongJin;
import com.zklc.weishangcheng.member.hibernate.persistent.HongbaoDian;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.GoodYongJinService;
import com.zklc.weishangcheng.member.service.HongbaoDianService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.util.HongBaoUtil;

@Service
public class GoodYongJinServiceImpl extends BaseServiceImp<GoodYongJin, Integer>
		implements GoodYongJinService {

	@Autowired
	private HongbaoDianService hongbaoDianService;
	@Autowired
	private UseryService useryService;
	
	@Override
	public Double findAllMoneyBuyUserIdAndType(Integer userId, int type) {
		StringBuffer hql = new StringBuffer("select sum(t.money) from good_yongjin t where 1=1 ");
		if(userId!=null){
			hql.append(" and t.toUserId="+userId);
			hql.append(" and t.status ="+type);	
			List<Double> money =super.findBySql(hql.toString(), null);
			if (null!=money) {
				return null==money.get(0)?0:money.get(0);
			}
		}
		return 0.0;
	}

	@Override
	public JSONObject fahongbao(Integer userId) {
		JSONObject json = new JSONObject();
		String hql = "from GoodYongJin g where g.toUserId ="+userId +" and g.status = 1";
		List<GoodYongJin> yongjins = findByHql(hql, null);
		List<GoodYongJin> updateList = new ArrayList<GoodYongJin>();
		Double money = 0.0;
		String yjids ="";
		if(yongjins.size()>0){
			for(GoodYongJin yongJin:yongjins){
				if((money+yongJin.getMoney()<=200.0)){
					money+=yongJin.getMoney();
//					yjids+=yongJin.getId();
					updateList.add(yongJin);
				}else{
					break;
				}
			}
		}
		if(money>0.0&&money<=200.0&&updateList.size()>0){
			String message = "发放未成功,请稍后再试!";
			Usery usery = useryService.findbyUserId(userId);
			if(usery!=null){
				money=new BigDecimal(money*100).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
				message = fahongbaozhu(usery.getWxOpenid(),userId,money.intValue(),yjids);
			}else{
				json.put("success", false);
				json.put("message", "用户数据出现错误,请联系管理员!");
			}
			if(message.equals("发放成功")||message.isEmpty()){
				for(GoodYongJin yongJin:updateList){
					yongJin.setStatus(2);
					yongJin.setUpdateTime(new Date());
				}
				saveOrUpdateAll(updateList);
				json.put("success", true);
				json.put("message", message);
			}else {
				json.put("success", false);
				json.put("message", message);
			}
		}else{
			json.put("message", "可提现金额已不足!");
		}
		return json;
	}
	
	public String fahongbaozhu(String wxOpenId,Integer userId,int amount,String yjdis) {
		String message="";
		String billNo = HongBaoUtil.createBillNo();
		SortedMap<String, String> map = HongBaoUtil.createMap(billNo, wxOpenId, String.valueOf(userId),amount);  
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
			 hongbao.setAddTime(new Date());
			 hongbao.setOpenid(wxOpenId);
			 hongbao.setAmount(amount);
			 hongbao.setBillNo(billNo);
			 hongbao.setRemark(responseXML);
			 hongbao.setBeizhu(yjdis);
			 hongbao.setUserId(userId);
			 String return_msg = "";
			 String return_code = "";
			 String result_code = "";
			 String send_listid = "";
			 try {
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
			} catch (Exception e) {
				e.printStackTrace();
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

}
