package com.zklc.weishangcheng.member.service.impl;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.LiuCode;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderLiu;
import com.zklc.weishangcheng.member.hibernate.persistent.Yongjin;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.LiuCodeService;
import com.zklc.weishangcheng.member.service.OrderLiuService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.service.YongjinService;
import com.zklc.weishangcheng.member.util.LeMianFlow;
@Service
public class OrderLiuServiceImpl extends BaseServiceImp<OrderLiu, Integer> implements OrderLiuService {
	@Autowired
	private UserService userService;
	@Autowired
	private YongjinService yongjinService;
	@Autowired
	private LiuCodeService liuCodeService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	
	@Override
	public OrderLiu findOrderByOrderBH(String ordersBH) {
		
		List<OrderLiu> list = this.findByProperty("ordersBH", ordersBH);
		if (list.size()!=0) {
			return list.get(0);
		}
		return null;
	}
	
	public int liuliangPay(OrderLiu order, String openid,Users user){
		//更新订单状态
		//order.setUserId(user.getUserId());
		order.setOrderStatus(1); //已支付
		order.setCreateDate(new Date());
		
		//验证价格与购买流量是否正确
		String busType = null;//运营商
		Integer num = Integer.parseInt(order.getPname().substring(6,order.getPname().length()-1));//购买流量数
		
		if(order.getPname().contains("电信")){
			busType = "3";
		}else if(order.getPname().contains("移动")){
			busType = "1";
		}else if(order.getPname().contains("联通")){
			busType = "2";
		}else{
			return 0;
		}
		String isG = order.getPname().substring(order.getPname().length()-1, order.getPname().length());
		if(isG.equals("G")){
			num = num*1024;
		}
		//根据流量运营商，购买流量数据，流量价值，判断数据库中是否存在，0 不存在，返回 ，大于1，数据异常返回，等于1 继续执行
		Integer isnum = Integer.parseInt(this.findBySql("SELECT COUNT(l.ordersId) FROM liucode l where l.busPerson='"+busType+"' and l.liuNum='"+num+"' and l.liuMoney = "+order.getMoney(), null).get(0).toString());
		if(isnum == 0 ){
			return 0;
		}else if(isnum > 1){
			return 0;
		}
		    
		
		
		
		//向上返佣金 20 30 50
		List list = yongjinService.findByProperty("orderId", order.getOrdersId());
		if(list.size()>0){
			for(int x=0;x<list.size();x++){
				Yongjin yong = (Yongjin) list.get(x);
				yong.setStatus(1);
				yong.setUpdateTime(new Date());
				yongjinService.update(yong);
				
			}
		}
		//充值流量-判断充值多少
		
		//String isG = order.getPname().substring(order.getPname().length()-1, order.getPname().length());
		Integer liuliang =0;
		String paramValue = null;
		if(isG.equals("G")){
			liuliang = Integer.parseInt(order.getPname().substring(6, order.getPname().length()-1));
			paramValue = String.valueOf(1024*liuliang);
		}else{
			paramValue = order.getPname().substring(6, order.getPname().length()-1);
		}
		LiuCode code = liuCodeService.findById(25);
		if(StringUtils.isNotBlank(order.getLiunum()) && order.getLiunum().contains("LLY")){
			String result = LeMianFlow.setOrderHuashi(order.getMobile(), order.getLiunum());
			JSONArray jsonArr = JSONArray.fromObject("["+result+"]");
	         JSONObject jet = jsonArr.getJSONObject(0);
	         String type = jet.get("res_code").toString();
	         
	         order.setChongzhiStatus(type);
	        if(type.equals("200")){
	        	 order.setDescription("成功");
		         order.setMsgId(jet.get("trade_no").toString());
	        }
			
		}else{
			
			String ontheway = null;
			if(StringUtils.isNotBlank(code.getLiustatus())){
				if(code.getLiustatus().equals("1")){
					ontheway = "OK";
				}else if(code.getLiustatus().equals("2")){
					ontheway = "OK";
				}else if(code.getLiustatus().equals("12")){
					ontheway = "OK";
				}
				//ontheway = code.getLiustatus();
			}
			//如果电信充值，走云通信
			if(order.getPname().contains("电信") || "OK".equals(ontheway) ){
				String result ;
				try {
					result = LeMianFlow.dataPlan(order.getMobile(), paramValue, order.getLiunum());
					JSONArray jsonArr = JSONArray.fromObject(result);
			         JSONObject jet = jsonArr.getJSONObject(0);
			         //{"statusCode":"000000","customId":"695136f5028d11e5a1610050568e55bd","statusMsg":"请求发送成功","rechargeId":"13f9a8989a7247f286c59d6f26de201c","status":"1"}
			         
			         order.setChongzhiStatus(jet.get("statusCode").toString());
					// order.setCustomId(jet.get("customId").toString());
			         order.setMsgId(jet.get("rechargeId").toString());
			         order.setDescription(jet.get("statusMsg").toString());
			         
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}else{
				String result = LeMianFlow.setOrderFlow(order.getMobile(), paramValue);
				 JSONArray jsonArr = JSONArray.fromObject("["+result+"]");
		         JSONObject jet = jsonArr.getJSONObject(0);
		         order.setChongzhiStatus(jet.get("status").toString());
		        
				 order.setDescription(jet.get("description").toString());
				
		         order.setMsgId(jet.get("msgid").toString());
			}
		}
		

		
		
		update(order);
		if(order.getChongzhiStatus().equals("350004") || order.getChongzhiStatus().equals("1401") || order.getChongzhiStatus().equals("1008")){
			
			code.setBusPerson("1");
			liuCodeService.update(code);
			if(order.getChongzhiStatus().equals("350004")){
				autosendmsgService.sendMsg("ozLHAvpfH45loRpQadUNQqRdwn9I", "云通讯需要充值了 ");
				autosendmsgService.sendMsg("osr0JszIptV-QCHYQ58aD7Loq3Yw", "云通讯需要充值了 ");
				autosendmsgService.sendMsg("ozLHAvk3hxJF74QxyYMlQNnH-218", "云通讯需要充值了 ");
			}else if(order.getChongzhiStatus().equals("1401")){
				autosendmsgService.sendMsg("ozLHAvpfH45loRpQadUNQqRdwn9I", "乐免需要充值了 ");
				autosendmsgService.sendMsg("osr0JszIptV-QCHYQ58aD7Loq3Yw", "乐免需要充值了 ");
				autosendmsgService.sendMsg("ozLHAvk3hxJF74QxyYMlQNnH-218", "乐免需要充值了 ");
			}else{
				autosendmsgService.sendMsg("ozLHAvpfH45loRpQadUNQqRdwn9I", "华时需要充值了 ");
				autosendmsgService.sendMsg("osr0JszIptV-QCHYQ58aD7Loq3Yw", "华时需要充值了 ");
				autosendmsgService.sendMsg("ozLHAvk3hxJF74QxyYMlQNnH-218", "华时需要充值了 ");
			}
			
			
			
		}
		return 0;
	}

	@Override
	public String saveOrder(Users user, OrderLiu order) {
		   String result = "false";
		   this.save(order);
		   //移动佣金最多不能超过17.5（266的11G流量）
		   if(order.getRetMoney() > 17.5){
			   return "error";
		   }
		   
		   DecimalFormat df = new DecimalFormat("######0.00");   
		   //创建分润
//		   if(user.getReferrerId() != null){
//			   Users ref = userService.findById(user.getReferrerId());
//			   if(ref != null && ref.getLevel() >= 1){
//				   Yongjin yong = new Yongjin();
//				   yong.setCreateDate(new Date());
//				   yong.setFromUserId(user.getUserId());
//				   yong.setLevel(1);
//				   yong.setMoney(Double.parseDouble(df.format(order.getRetMoney()*0.5)));
//				   yong.setOrderId(order.getOrdersId());
//				   yong.setStatus(0);
//				   yong.setToUserId(ref.getUserId());
//				   yongjinService.save(yong);
//				   
//			   }
//			   if(ref != null && ref.getReferrerId() != null){
//				   Users ref2 = userService.findById(ref.getReferrerId());
//				   if(ref2!= null && ref2.getLevel() >= 2){
//					   Yongjin yong2 = new Yongjin();
//					   yong2.setCreateDate(new Date());
//					   yong2.setFromUserId(user.getUserId());
//					   yong2.setLevel(2);
//					   yong2.setMoney(Double.parseDouble(df.format(order.getRetMoney()*0.3)));
//					   yong2.setOrderId(order.getOrdersId());
//					   yong2.setStatus(0);
//					   yong2.setToUserId(ref2.getUserId());
//					   yongjinService.save(yong2);
//					 
//				   }
//				   if(ref2 != null && ref2.getReferrerId() != null){
//					   Users ref3 = userService.findById(ref2.getReferrerId());
//					   if(ref3 != null && ref3.getLevel() >= 3){
//						   Yongjin yong3 = new Yongjin();
//						   yong3.setCreateDate(new Date());
//						   yong3.setFromUserId(user.getUserId());
//						   yong3.setLevel(3);
//						   yong3.setMoney(Double.parseDouble(df.format(order.getRetMoney()*0.2)));
//						   yong3.setOrderId(order.getOrdersId());
//						   yong3.setStatus(0);
//						   yong3.setToUserId(ref3.getUserId());
//						   yongjinService.save(yong3);
//					   }
//				   }
//			   }
//		  
//		   } 
		   result = "true";
		return result;
		  
	}

	@Override
	public List checkLiuDateIsTure(String busType, String num, String money) {
		List list = liuCodeService.findByHql(" FROM LiuCode l where l.busPerson='"+busType+"' and l.liuNum='"+num+"' and l.liuMoney = "+money, null);
		if(list == null){
			list = new ArrayList();
		}
		return list;
	}
	
	
	
	
}
