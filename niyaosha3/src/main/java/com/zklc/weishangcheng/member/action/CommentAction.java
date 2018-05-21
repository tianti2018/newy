package com.zklc.weishangcheng.member.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;


import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderJinHuo;
import com.zklc.weishangcheng.member.hibernate.persistent.OrderAddress;
import com.zklc.weishangcheng.member.hibernate.persistent.ProductComment;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.hibernate.persistent.vo.ProductCommentVo;
import com.zklc.weishangcheng.member.service.CommentService;
import com.zklc.weishangcheng.member.service.JiFenRecordService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weixin.util.WeixinUtil;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/comment")
@Action(value = "commentAction")
@Results({
		@Result(name = "saveCommet", location = "/WEB-INF/jsp/mianMoBuy.jsp"),//我的订单
		@Result(name = "commentList", location = "/WEB-INF/jsp/mianMoBuy.jsp"),
		@Result(name = "products", location = "/WEB-INF/jsp/products.jsp"),
})
public class CommentAction extends BaseAction{
	@Autowired
	private UseryService useryService;
	@Autowired
	private CommentService commentservice;
	
	private Users user;
	private Integer pageNum;
	private OrderJinHuo order;
	private OrderAddress orderAddress;
	private ProductComment productComment;
	public String loginName;// 用户登录名
	public String userName;
	public Integer userId ;// 用户id
	public Integer prodId;
	public Double userFhrecord;
	public Integer cardNum;// 可用卡密数量
	public String payType;// 支付方式(1卡密 2积分 3微信支付)
	public String message;
	public String textMessage;
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
	
	/*
	 * 插入评论
	 */
	public String saveCommet(){
		userVo = getSessionUser();
		user = userVo.getUser();
		userId=user.getUserId();
		userName=user.getUserName();
		ProductComment pro = new ProductComment();
		Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置显示格式
        String nowDate= df.format(dt.getTime()); 
        Date nowdate = null;
        try {
			nowdate = df.parse(nowDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		pro.setUserId(userId);
		pro.setUserName(userName);;
		pro.setComment(textMessage);
		pro.setProdId(prodId);
		pro.setCreatetime(dt);
		commentservice.save(pro);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		response=ServletActionContext.getResponse();
		jsonMap.put("success", true);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/*
	 * 查询评论
	 */
	public String commentList(){
		userVo = getSessionUser();
		user = userVo.getUser();
		userId=user.getUserId();
		List<ProductComment> list=commentservice.commentList(prodId, pageNum);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		request.setAttribute("comlist", list);
		request.setAttribute("length", list.size());
		return "commentList";
	}
	/*
	 * 懒加载查询
	 */
	public String ajaxcommentList(){
		userVo = getSessionUser();
		user = userVo.getUser();
		userId=user.getUserId();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<Object[]> list=(List)commentservice.commentList(prodId, pageNum);
		List<ProductCommentVo> clist=new ArrayList<ProductCommentVo>();
		for(int i=0;i<list.size();i++){
			Object[] objs=list.get(i);
			ProductCommentVo pro= new ProductCommentVo();
			String name=(String)objs[8];
			if(objs[8].toString().length()>=3){
				String newname=name.substring(1, objs[8].toString().length()-1);
				name=name.replace(newname, "***");
			}else{
				String newname=name.substring(0,1);
				name=name.replace(newname, "*");
			}
			Date time=(Date)objs[3];
			String com=(String)objs[2];
			pro.setUserName(name);
			pro.setCreatetime(time);
			pro.setComment(com);
			clist.add(pro);
		}
		response=ServletActionContext.getResponse();
		/**************组装json*****************/
        if(clist!=null && clist.size()>0){
            jsonMap.put("success", true);
            jsonMap.put("children", clist);
        }else{
        	jsonMap.put("success", false);
        	jsonMap.put("length", clist.size());
        }
        JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getTextMessage() {
		return textMessage;
	}
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}
	
	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

}
