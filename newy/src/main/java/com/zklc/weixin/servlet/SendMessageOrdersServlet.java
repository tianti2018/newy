package com.zklc.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.service.UsersService;
import com.zklc.weishangcheng.member.service.impl.UsersServiceImpl;
import com.zklc.weishangcheng.member.service.impl.WeixinAutosendmsgServiceImpl;
import com.zklc.weixin.util.UserInfoUtil;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class SendMessageOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {

		        response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();

		      
		        //type 1:发货信息 2：提现信息
		       
		        String type = (String) request.getParameter("type");
		        String wxOpenId =  (String) request.getParameter("wxOpenId");
		       
		       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    	   sdf.format(new Date());
	    	   UsersService service = new UsersServiceImpl();
	    	   WeixinAutosendmsgServiceImpl autosendmsgServiceImpl = new WeixinAutosendmsgServiceImpl();
	    	   Users user = service.findUserByOpenId(wxOpenId);
	    	   Users refferUser = service.findReferrerUser(user);
	    	   UserInfoUtil userInfo = autosendmsgServiceImpl.processUserInfoObject(wxOpenId);
		        
		        System.out.println("-------------type-----"+type);
		        System.out.println("-------------type-----"+type);
		        System.out.println("-------------type-----"+type);
		        System.out.println("-------------wxOpenId-----"+wxOpenId);
		        System.out.println("-------------wxOpenId-----"+wxOpenId);
		        System.out.println("-------------wxOpenId-----"+wxOpenId);
		        if(StringUtils.isNotEmpty(type) && StringUtils.isNotEmpty(wxOpenId)){
		        	 if(type.equals("1")){
		        		 String kuaiNo = (String) request.getParameter("kuaidiNo");
		        		 String kuaiName = (String) request.getParameter("kuaidiName");
		        		 String orderNo = (String) request.getParameter("orderNo");
		        		 System.out.println("-------------kuaiNo-----"+kuaiNo);
		 		        System.out.println("-------------kuaiNo-----"+kuaiNo);
		 		        System.out.println("-------------kuaiNo-----"+kuaiNo);
		 		       System.out.println("-------------kuaiName-----"+kuaiName);
		 		        System.out.println("-------------kuaiName-----"+kuaiName);
		 		        System.out.println("-------------kuaiName-----"+kuaiName);
		 		       System.out.println("-------------orderNo-----"+orderNo);
		 		        System.out.println("-------------orderNo-----"+orderNo);
		 		        System.out.println("-------------orderNo-----"+orderNo);
		 		        
		 		        System.out.println("-------------Integer.parseInt(kuaiName)=="+Integer.parseInt(kuaiName));
		 		       System.out.println("-------------Integer.parseInt(kuaiName)=="+Integer.parseInt(kuaiName));
		 		       switch (Integer.parseInt(kuaiName)) {
						case 0:
							kuaiName = "圆通";
							break;
						case 1:
							kuaiName = "顺丰";
							break;
						case 2:
							kuaiName = "申通";//申通
							break;
						case 3:
							kuaiName = "中通";
							break;
						case 4:
							kuaiName = "韵达";
							break;
						case 5:
							kuaiName = "EMS";
							break;
						case 6:
							kuaiName = "宅急送";
							break;
						case 7:
							kuaiName = "全峰";
							break;
						case 8:
							kuaiName = "天天快递";
							break;
						case 9:
							kuaiName = "自提";
							break;
						}

		 		        System.out.println("-=-----1111-------------kuaiName="+kuaiName);
		 		        
				        	if(StringUtils.isNotEmpty(kuaiNo) && StringUtils.isNotEmpty(kuaiName)&& StringUtils.isNotEmpty(orderNo)){
				        		service.sendMsg(wxOpenId,"您好:\n 您的订单："+orderNo+"已为您发货 \n 快递公司："+kuaiName+" \n 快递单号："+kuaiNo);
				        	}
				       }else if(type.equals("2")){
				    	   String txMoney = (String) request.getParameter("txMoney");
			        	   String txDate = (String) request.getParameter("txDate");
			        	   System.out.println("-------------txMoney-----"+txMoney);
			 		        System.out.println("-------------txMoney-----"+txMoney);
			 		        System.out.println("-------------txMoney-----"+txMoney);
			 		       System.out.println("-------------txDate-----"+txDate);
			 		        System.out.println("-------------txDate-----"+txDate);
			 		        System.out.println("-------------txDate-----"+txDate);
			        	   if(StringUtils.isNotEmpty(txMoney) && StringUtils.isNotEmpty(txDate)){
			        		   service.sendMsg(wxOpenId,"您好:\n 您于"+txDate+"申请提现的"+txMoney+"元，已通过系统审核！");
			        	   }
				       }else if(type.equals("3")){//退货申请,给上家推消息
				    	   if(user !=null && refferUser != null && userInfo != null){
				    		   service.sendMsg(refferUser.getWxOpenid(), "您好:\n 您推荐的["+userInfo.getNickname()+"]在申请退货,这有可能导致您无法抽奖或提现,请知悉!");
				    	   }
				       }else if(type.equals("4")){//退货成功,给上家推消息
				    	   if(user !=null && refferUser != null && userInfo != null){
				    		   service.sendMsg(refferUser.getWxOpenid(), "您好:\n 您推荐的["+userInfo.getNickname()+"]已退货成功,如果您无法抽奖或提现,请联系客服!");
				    	   }
				       }else if(type.equals("5")){//退货申请,给上家推消息
				    	   if(user !=null && refferUser != null && userInfo != null){
				    		   service.sendMsg(refferUser.getWxOpenid(), "您好:\n 您推荐的["+userInfo.getNickname()+"]取消退货,退货导致的抽奖与体现影响会消失,请知悉!");
				    	   }
				       }
		        }
		       
		    }

}
