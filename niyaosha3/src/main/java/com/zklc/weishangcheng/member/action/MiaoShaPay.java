package com.zklc.weishangcheng.member.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.utils.GetWxOrderno;
import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.dao.OrderDao;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.service.OrderService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.service.YongjinService;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/pay")
@Action(value = "miaoshaAction")
public class MiaoShaPay extends BaseAction {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private UseryService useryService;
	@Autowired
	private YongjinService yongjinService;
	
	private OrderJinHuo order;
	private Integer userId;
	private String ordersBH;
	private String code;// 微信code
	
	private String wxOpenid;// 用户微信标识符
	/**
	 * 订单支付成功通知
	 */
	@SuppressWarnings("rawtypes")
	public String execute() {
		String openid = null;
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
	        String line = null;
	        StringBuilder sb = new StringBuilder();
	        while((line = br.readLine())!=null){
	            sb.append(line);
	        }
        Map map = null;
		try {
			map = GetWxOrderno.doXMLParse(sb.toString());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		String return_code = (String) map.get("return_code");
	    openid = (String) map.get("openid");
		String out_trade_no = (String) map.get("out_trade_no");
//		String total_fee = (String) map.get("total_fee");
		
	  	PrintWriter out = response.getWriter();
	  	String returnStr="FAIL";
	  	
		out.println(return_code);
	  	out.flush();
		//支付成功
		if(null!=return_code&&return_code.equals("SUCCESS")){
			order = orderDao.findOrderByOrderBH(out_trade_no);
			
			/*if(null!=order){
				if(order.get==0){
					System.out.println("order.getOrderStatus() "+order.getOrderStatus());
					
					returnStr="SUCCESS";
					//信息通知用户
					String content="您好:\n您的订单已支付成功！\n"+"订单编号："+order.getOrdersBH()+"\n"+"支付金额："+order.getMoney()+"元";
					autosendmsgService.sendMsg(openid, content);
					order.setOrderStatus(1);
					order.setCreateDate(new Date());
					orderService.update(order);
				}
			}*/
		}
		System.out.println("秒杀订单支付返回结果是："+returnStr);
		out.close();
	   } 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
    	return null;
	}
	
	public OrderJinHuo getOrder() {
		return order;
	}

	public void setOrder(OrderJinHuo order) {
		this.order = order;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getOrdersBH() {
		return ordersBH;
	}

	public void setOrdersBH(String ordersBH) {
		this.ordersBH = ordersBH;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

}
