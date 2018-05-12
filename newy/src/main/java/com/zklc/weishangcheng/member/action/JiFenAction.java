package com.zklc.weishangcheng.member.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.event.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.City;
import com.zklc.weishangcheng.member.hibernate.persistent.JiFenRecord;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.MiaoShaOrder;
import com.zklc.weishangcheng.member.hibernate.persistent.Order;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.UsersService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weixin.util.SystemMessage;
import com.zklc.weixin.util.WeixinUtil;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/jifen")
@Action(value = "jifenAction")
@Results({
		@Result(name = "myJiFenList", location = "/WEB-INF/jsp/jifen/phone_my_jifen.jsp"),//我的订单
		@Result(name = "product", location = "/WEB-INF/jsp/product.jsp"),
		@Result(name = "products", location = "/WEB-INF/jsp/products.jsp"),
})
public class JiFenAction extends BaseAction {
	@Autowired
	private UsersService userService;
	@Autowired
	private JiFenRecordService jfrecordService;
	@Autowired
	private UseryService useryService;
	@Autowired
	private JiFenRecordService fenRecordService;

	private Users user;
	private Integer pageNum;
	private Order order;
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
	private Integer status;
	
	public Integer getPageNum() {
		return pageNum;
	}



	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public void myJifen(){
		user = getSessionUser();
		JSONObject json = new JSONObject();
		json.put("success", false);
		if(user == null){
			json.put("timeOut", false);
		}else{
			Integer zongJifen = jfrecordService.findbyUserIdAndStatus(user.getUserId(),1);
			json.put("kyjifen", zongJifen);
			json.put("success", true);
		}
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String myJiFenList(){
		//获取用户信息
		user = getSessionUser();
//		user = userService.findById(1883);
		if(user == null){
			return "timeOut";
		}
		//获得查询订单的起止时间和用户id
		Integer uId=null;
		if(userId==null||userId.equals(""))
			uId=user.getUserId();
		else
			uId=userId;
//		System.out.println(uId);
		List<JiFenRecord> myjifen = jfrecordService.findJiFenList(uId,status, date1, date2,pageNum);
		if(status==null||status.equals("")){
			status=11;
		}
		request.setAttribute("status", status);
		request.setAttribute("myjifen", myjifen);
		request.setAttribute("length", myjifen.size());
		return "myJiFenList";
			
	}

	public String ajaxmyJiFenList(){
		user = getSessionUser();
		if(user == null){
			return "timeOut";
		}
		//获得查询订单的起止时间和用户id
		Integer uId=null;
		if(userId==null||userId.equals(""))
			uId=user.getUserId();
		else
			uId=userId;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(status==null||status.equals("")){
			status=11;
		}
		List<JiFenRecord> mo1  = jfrecordService.findJiFenList(uId,status, date1, date2,pageNum);
		response=ServletActionContext.getResponse();
		/**************组装json*****************/
        if(mo1!=null && mo1.size()>0){
            jsonMap.put("success", true);
            jsonMap.put("children", mo1);
            jsonMap.put("status", status);
        }else{
        	jsonMap.put("success", false);
        	jsonMap.put("length", mo1.size());
        }
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
		
	}
	
	/**
	 * 对数据库中的memo字段进行批量修改
	 * @by zhang_wanqiang
	 * @throws SQLException
	 *//*
	public void editMemo() throws SQLException{
		String hql = "from JiFenRecord j where jifen  in(1,2) and memo LIKE '新超级粉丝%' and status = 1"; //写一个查询的hql语句，查出积分等于1和2的数据
		List<JiFenRecord> jiFenRecords = fenRecordService.findByHql(hql, null); //执行hql语句，得到结果jiFenRecords
		List<JiFenRecord> updateList = new ArrayList<JiFenRecord>();  //定义一个updateList集合
		String memo ="";
		for(JiFenRecord jifen:jiFenRecords){  //遍历得到的结果集jiFenRecords
			if(jifen.getJifen()==1){
				memo = "新忠实粉丝[";
			}else if(jifen.getJifen()==2){
				memo = "新铁杆粉丝[";
			}
			memo+=jifen.getFromUserId()+"]关注，赠送["+jifen.getUserId()+"]"+jifen.getJifen()+"积分";
			System.out.println(memo);
			jifen.setMemo(memo);
			updateList.add(jifen);
		}
		fenRecordService.saveOrUpdateAll(updateList);
		System.out.println("over"+updateList.size());
	}
	
	*//**
	 * 对数据库中的memo字段进行批量修改
	 * @by zhang_wanqiang
	 * @throws SQLException
	 *//*
	public void editMemo1() throws SQLException{
		String hql = "from JiFenRecord j where j.memo LIKE '%60积分' AND j.jifen <60 and j.status = 1";
		List<JiFenRecord> jiFenRecords = fenRecordService.findByHql(hql, null); //执行hql语句，得到结果jiFenRecords
		
		List<JiFenRecord> updateList = new ArrayList<JiFenRecord>();  //定义一个updateList集合
		
		for(JiFenRecord jifen:jiFenRecords){  //遍历得到的结果集jiFenRecords
			String memo="新超级粉丝["+jifen.getFromUserId()+"]关注，赠送["+jifen.getUserId()+"]5积分";
			System.out.println(memo);
			jifen.setMemo(memo);
			updateList.add(jifen);
		}
		fenRecordService.saveOrUpdateAll(updateList);
		System.out.println("over"+updateList.size());
	}*/
	
	/**
	 * 对数据库中jifen_record表的的userId字段进行批量修改
	 * @by zhang_wanqiang
	 * @throws SQLException
	 */
	public void editUserId() throws SQLException{

		String hql1 = "from JiFenRecord j where j.userId IS NULL AND j.jifen = 60 ";
		List<JiFenRecord> jiFenRecord = fenRecordService.findByHql(hql1, null); //执行hql语句，得到结果jiFenRecords
		
//		String hql2 = "from JifenUser j  where j.userId >= 14124514 AND j.userId <= 14147431";
//		List<JifenUser> JifenUser = fenRecordService.findByHql(hql2, null); //执行hql语句，得到结果jiFenRecords

		List<JiFenRecord> updateList = new ArrayList<JiFenRecord>();  //定义一个updateList集合
		int i = 14124514;
		for(JiFenRecord jifen:jiFenRecord){  //遍历得到的结果集jiFenRecords
			if(i>=14124514&&i<=14147431){
				jifen.setUserId(i);
				updateList.add(jifen);
				i++;
			}
		}
		fenRecordService.saveOrUpdateAll(updateList);
	}
	
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	private Users getSessionUser(){
		
		 user = (Users) request.getSession().getAttribute("loginUser");
		 if(user==null){
			 if(!StringUtils.isNotEmpty(code)){
					try {
						code = request.getParameter("code");
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(!StringUtils.isNotEmpty(wxOpenid)){
					 wxOpenid=(String) request.getSession().getAttribute("wxOpenid");
				}
			  if(code!=null){
				  if(wxOpenid==null){
					  System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> code333------ "+code);
					  wxOpenid = WeixinUtil.code2openid(code);
					  request.getSession().setAttribute("wxOpenid",wxOpenid);
				  }
			  }
			 if(wxOpenid!=null){
				 Usery usery = useryService.findbyWxOpenId(wxOpenid);
				 if(usery!=null){
					 user = useryService.findJifenUserByUsery(usery);
					 if (user!=null) {
						 request.getSession().setAttribute("loginUser",user);
					 }
				}
			 }
		 }
		 if(user!=null&&!"".equals(user.getAppCenterId())&& null != user.getAppCenterId() && user.getAppCenterId().equals(2)){
			 return null;
		 }
		 
			 //user = userService.findById(7291);
		
		// user =userService.findById(2341);
		return  user;
	}


	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	} 
	
	
}
