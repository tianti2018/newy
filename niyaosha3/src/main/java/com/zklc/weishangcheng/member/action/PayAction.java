package com.zklc.weishangcheng.member.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
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
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Order;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderLiu;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.OrderLiuService;
import com.zklc.weishangcheng.member.service.OrderService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.WeixinAutosendmsgService;
import com.zklc.weishangcheng.member.service.YongjinService;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/pay")
@Action(value = "payAction")
public class PayAction extends BaseAction {
	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WeixinAutosendmsgService autosendmsgService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private OrderLiuService orderLiuService;
	@Autowired
	private YongjinService yongjinService;
	
	private Users user;
	private Order order;
	private OrderLiu orderLiu;
	private Integer userId;
	private String ordersBH;
	private String code;// 微信code
	
	private String wxOpenid;// 用户微信标识符
	/**
	 * 订单支付成功通知
	 */
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
		String total_fee = (String) map.get("total_fee");
		
	  	PrintWriter out = response.getWriter();
	  	String returnStr="FAIL";
	  	
	  /*	String return_code = "SUCCESS";
	  	
		
		String out_trade_no ="99201512012329111433766";
		
		 openid = "ozLHAvvRulwm4tvq96Axw6_Zn0h8";
		*/
		out.println(return_code);
	  	out.flush();
		//String total_fee = (String) map.get("total_fee");
			//支付成功
			if(null!=return_code&&return_code.equals("SUCCESS")){
				if(out_trade_no.startsWith("99")){
					//流量
					orderLiu = orderLiuService.findOrderByOrderBH(out_trade_no);
				}else{
					order = orderService.findOrderByOrderBH(out_trade_no);
				}
				
				if(null!=order){
					if(order.getOrderStatus()==0){
						System.out.println("order.getOrderStatus() "+order.getOrderStatus());
						
						returnStr="SUCCESS";
						//信息通知用户
						String content="您好:\n您的订单已支付成功！\n"+"订单编号："+order.getOrdersBH()+"\n"+"支付金额："+order.getMoney()+"元";
						autosendmsgService.sendMsg(openid, content);
						user = userService.findById(order.getUserId());

						orderService.moneyPay(order,openid,user);
						
						if(!out_trade_no.startsWith("ms")){
							Users refferUser = null;
							Usery parentUsery = null;
//							if(user.getReferrerId()!=null){
//								refferUser = userService.findById(user.getReferrerId());
//								parentUsery = useryService.findbyUserId(user.getReferrerId());
//							}
							String mess = "";
							if(refferUser !=null){
								mess = "您好:\n您的超级粉丝:";
								mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+order.getMoney()+"元!";
								if(parentUsery !=null&&parentUsery.getWxOpenid()!=null)
								autosendmsgService.sendMsg(parentUsery.getWxOpenid(),mess);
//								if(refferUser.getReferrerId()!=null){
//									Users r2 = userService.findById(refferUser.getReferrerId());
//									if(r2!=null){
//										Usery usery2 = useryService.findbyUserId(r2.getUserId());
//										if(usery2!=null&&usery2.getWxOpenid()!=null){
//											mess = "您好:\n您的铁杆粉丝:";
//											mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+order.getMoney()+"元";
//											autosendmsgService.sendMsg(usery2.getWxOpenid(),mess);
//										}
//										if(r2.getReferrerId()!=null){
//											Usery u3 = useryService.findbyUserId(r2.getReferrerId());
//											if(u3!=null&&u3.getWxOpenid()!=null){
//												mess = "您好:\n您的忠实粉丝:";
//												mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+order.getMoney()+"元!";
//												autosendmsgService.sendMsg(u3.getWxOpenid(),mess);
//											}
//										}
//									}
//								}
							}
						}
					}
				}else{
					if(orderLiu.getOrderStatus()==0){
						System.out.println("orderLiu.getOrderStatus() "+orderLiu.getOrderStatus());
						
						returnStr="SUCCESS";
						//信息通知用户
						String content="您好:\n您的订单已支付成功！\n"+"订单编号："+orderLiu.getOrdersBH()+"\n"+"支付金额："+orderLiu.getMoney()+"元";
						autosendmsgService.sendMsg(openid, content);
						user = userService.findById(orderLiu.getUserId());
						//判断订单类型
						Integer num  = orderLiuService.liuliangPay(orderLiu,openid,user);
						
						Users refferUser = null;
						Usery parentUsery = null;
//						if(user.getReferrerId()!=null){
//							refferUser = userService.findById(user.getReferrerId());
//							parentUsery = useryService.findbyUserId(user.getReferrerId());
//						}
						
						String mess = "";
						if(refferUser !=null){
							
							
							mess = "您好:\n您的超级粉丝:";
							mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+orderLiu.getMoney()+"元";
							
							//返给各级别佣金
							List list = yongjinService.findBySql("SELECT if(sum(y.money)>0,sum(y.money),0) FROM yongjin y where y.orderId="+orderLiu.getOrdersId()+" and y.toUserId="+refferUser.getUserId(), null);
							mess += "，您获得佣金"+list.get(0).toString()+"元";
							
							if(parentUsery !=null&&parentUsery.getWxOpenid()!=null)
							autosendmsgService.sendMsg(parentUsery.getWxOpenid(),mess);
//							if(refferUser.getReferrerId()!=null){
//								Users r2 = userService.findById(refferUser.getReferrerId());
//								if(r2!=null){
//									Usery usery2 = useryService.findbyUserId(r2.getUserId());
//									if(usery2!=null&&usery2.getWxOpenid()!=null){
//										mess = "您好:\n您的铁杆粉丝:";
//										mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+orderLiu.getMoney()+"元";
//										
//									
//										//返给各级别佣金
//										List list1 = yongjinService.findBySql("SELECT if(sum(y.money)>0,sum(y.money),0) FROM yongjin y where y.orderId="+orderLiu.getOrdersId()+" and y.toUserId="+usery2.getUserId(), null);
//										mess += "，您获得佣金"+list1.get(0).toString()+"元";
//										
//										autosendmsgService.sendMsg(usery2.getWxOpenid(),mess);
//									}
//									if(r2.getReferrerId()!=null){
//										Usery u3 = useryService.findbyUserId(r2.getReferrerId());
//										if(u3!=null&&u3.getWxOpenid()!=null){
//											mess = "您好:\n您的忠实粉丝:";
//											mess += "【"+user.getUserId()+" ： "+user.getUserName()+"】已成功下单！\n"+"支付金额："+orderLiu.getMoney()+"元";
//											//返给各级别佣金
//											List list1 = yongjinService.findBySql("SELECT if(sum(y.money)>0,sum(y.money),0) FROM yongjin y where y.orderId="+orderLiu.getOrdersId()+" and y.toUserId="+u3.getUserId(), null);
//											mess += "，您获得佣金"+list1.get(0).toString()+"元";
//											
//											autosendmsgService.sendMsg(u3.getWxOpenid(),mess);
//										}
//									}
//								}
//							}
						}
					}
				}
			}
		//}
		System.out.println(new Date().toLocaleString()+",订单支付返回结果是："+returnStr);
		out.close();
	   } 
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
    	return null;
	}
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
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

	/**
	 * @return the orderLiu
	 */
	public OrderLiu getOrderLiu() {
		return orderLiu;
	}

	/**
	 * @param orderLiu the orderLiu to set
	 */
	public void setOrderLiu(OrderLiu orderLiu) {
		this.orderLiu = orderLiu;
	}
	
}
