package com.zklc.weishangcheng.member.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.ShouYiForUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.XingHuoQuanRecord;
import com.zklc.weishangcheng.member.service.ShouYiForUserService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weishangcheng.member.service.XingHuoQuanRecordService;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/shouyi")
@Action(value = "shouyiAction")
@Results({
		@Result(name = "myshouyiList", location = "/WEB-INF/jsp/shouyi/phone_my_shouyi.jsp"),//我的订单
		@Result(name = "product", location = "/WEB-INF/jsp/product.jsp"),
		@Result(name = "products", location = "/WEB-INF/jsp/products.jsp"),
})
public class shouyiAction extends BaseAction {
	@Autowired
	private UserService userService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private ShouYiForUserService shouyiService;

	private Users user;
	private Integer pageNum;
	private OrderJinHuo order;
	private OrderAddress orderAddress;
	public String loginName;// 用户登录名
	public Integer userId ;// 用户id
	public Double userFhrecord;
	public Integer cardNum;// 可用卡密数量
	public String payType;// 支付方式(1卡密 2积分 3微信支付)
	public String message;
	public String code;
	public String rcode;
	public String wxOpenid;
	public String returnUrl;
	public String orderNo;
	public String securityCode;// 安全码
	public Double allScore;
	public Double currScore;
	public Integer orderId;
	public List orderAddressList;
	private String centerLoginName;// 服务中心
	private String referrerLoginName;// 推荐人
	public String currentOrderPage;
	private String total_price1;
	private Integer orderAddRessId;
	public Integer activityId;
	public Integer eggPrizeUserId;
	public String pwxOpenId;
	private String qty_item_1;
	
	private String color;
	private String size;
	private String orderType;
	private Integer qi;//秒杀活动第几期
	private Integer miaoShaNum;//秒杀活动产品数量
	private String requestType;//订单访问类型
	private String levelValue;
	private String date1;
	private String date2;
	
	public Integer getPageNum() {
		return pageNum;
	}



	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}



	public String myXingHuoQuanList(){
		//获取用户信息
		userVo = getSessionUser();
		if(userVo == null){
			return "timeOut";
		}
		//获得查询订单的起止时间和用户id
		return "myXingHuoQuanList";
			
	}

//	public String ajaxmyXingHuoQuanList(){
//		userVo = getSessionUser();
//		user = userVo.getUser();
//		if(user == null){
//			return "timeOut";
//		}
//		//获得查询订单的起止时间和用户id
//		Integer uId=null;
//		if(userId==null||userId.equals(""))
//			uId=user.getUserId();
//		else
//			uId=userId;
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		//	System.out.println(date1+":::"+date2);
//			List<XingHuoQuanRecord> mo1  = xinghuoquanservice.findXingHuoQuanList(uId, date1, date2,pageNum);
//			System.out.println(mo1.size());
//		//	JSONObject json=new JSONObject();
//			response=ServletActionContext.getResponse();
//			/**************组装json*****************/
//            if(mo1!=null && mo1.size()>0){
////            	for(int i=0;i<mo1.size();i++){
////            	}
//            	jsonMap.put("success", true);
//            	jsonMap.put("children", mo1);
//            	jsonMap.put("length", mo1.size());
//            }else{
//        		jsonMap.put("success", false);
//            }
//            JSONObject jsonObject = JSONObject.fromObject(jsonMap);
//		try {
//			response.getWriter().print(jsonObject.toString());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
//		return null;
//		
//	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	


	public OrderJinHuo getOrder() {
		return order;
	}



	public void setOrder(OrderJinHuo order) {
		this.order = order;
	}



	public OrderAddress getOrderAddress() {
		return orderAddress;
	}



	public void setOrderAddress(OrderAddress orderAddress) {
		this.orderAddress = orderAddress;
	}



	public String getLoginName() {
		return loginName;
	}



	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public Double getUserFhrecord() {
		return userFhrecord;
	}



	public void setUserFhrecord(Double userFhrecord) {
		this.userFhrecord = userFhrecord;
	}



	public Integer getCardNum() {
		return cardNum;
	}



	public void setCardNum(Integer cardNum) {
		this.cardNum = cardNum;
	}



	public String getPayType() {
		return payType;
	}



	public void setPayType(String payType) {
		this.payType = payType;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getRcode() {
		return rcode;
	}



	public void setRcode(String rcode) {
		this.rcode = rcode;
	}



	public String getWxOpenid() {
		return wxOpenid;
	}



	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}



	public String getReturnUrl() {
		return returnUrl;
	}



	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}



	public String getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}



	public String getSecurityCode() {
		return securityCode;
	}



	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}



	public Double getAllScore() {
		return allScore;
	}



	public void setAllScore(Double allScore) {
		this.allScore = allScore;
	}



	public Double getCurrScore() {
		return currScore;
	}



	public void setCurrScore(Double currScore) {
		this.currScore = currScore;
	}



	public Integer getOrderId() {
		return orderId;
	}



	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}



	public List getOrderAddressList() {
		return orderAddressList;
	}



	public void setOrderAddressList(List orderAddressList) {
		this.orderAddressList = orderAddressList;
	}



	public String getCenterLoginName() {
		return centerLoginName;
	}



	public void setCenterLoginName(String centerLoginName) {
		this.centerLoginName = centerLoginName;
	}



	public String getReferrerLoginName() {
		return referrerLoginName;
	}



	public void setReferrerLoginName(String referrerLoginName) {
		this.referrerLoginName = referrerLoginName;
	}



	public String getCurrentOrderPage() {
		return currentOrderPage;
	}



	public void setCurrentOrderPage(String currentOrderPage) {
		this.currentOrderPage = currentOrderPage;
	}



	public String getTotal_price1() {
		return total_price1;
	}



	public void setTotal_price1(String total_price1) {
		this.total_price1 = total_price1;
	}



	public Integer getOrderAddRessId() {
		return orderAddRessId;
	}



	public void setOrderAddRessId(Integer orderAddRessId) {
		this.orderAddRessId = orderAddRessId;
	}



	public Integer getActivityId() {
		return activityId;
	}



	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}



	public Integer getEggPrizeUserId() {
		return eggPrizeUserId;
	}



	public void setEggPrizeUserId(Integer eggPrizeUserId) {
		this.eggPrizeUserId = eggPrizeUserId;
	}



	public String getPwxOpenId() {
		return pwxOpenId;
	}



	public void setPwxOpenId(String pwxOpenId) {
		this.pwxOpenId = pwxOpenId;
	}



	public String getQty_item_1() {
		return qty_item_1;
	}



	public void setQty_item_1(String qty_item_1) {
		this.qty_item_1 = qty_item_1;
	}



	public String getColor() {
		return color;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public String getSize() {
		return size;
	}



	public void setSize(String size) {
		this.size = size;
	}



	public String getOrderType() {
		return orderType;
	}



	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}



	public Integer getQi() {
		return qi;
	}



	public void setQi(Integer qi) {
		this.qi = qi;
	}



	public Integer getMiaoShaNum() {
		return miaoShaNum;
	}



	public void setMiaoShaNum(Integer miaoShaNum) {
		this.miaoShaNum = miaoShaNum;
	}



	public String getRequestType() {
		return requestType;
	}



	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}



	public String getLevelValue() {
		return levelValue;
	}



	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}



	public String getDate1() {
		return date1;
	}



	public void setDate1(String date1) {
		this.date1 = date1;
	}



	public String getDate2() {
		return date2;
	}



	public void setDate2(String date2) {
		this.date2 = date2;
	} 
	
	
}
