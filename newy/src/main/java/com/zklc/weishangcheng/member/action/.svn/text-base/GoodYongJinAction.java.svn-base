package com.zklc.weishangcheng.member.action;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.GoodYongJinService;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weixin.util.WeixinUtil;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/goodyj")
@Action(value = "goodYJAction")
public class GoodYongJinAction extends BaseAction {

	@Autowired
	private GoodYongJinService goodYongJinService;
	@Autowired
	private JifenUserService userService;
	@Autowired
	private UseryService useryService;
	
	private JifenUser user;
	
	private String code;
	private String wxOpenid;
	
	private JifenUser getSessionUser(){
		
		 user = (JifenUser) request.getSession().getAttribute("loginUser");
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
		 
		return  user;
	}
	
	public void tixianGoodYJ(){
		user = getSessionUser();
		JSONObject json =goodYongJinService.fahongbao(user.getUserId());
		try {
			ServletActionContext.getResponse().getWriter().print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JifenUser getUser() {
		return user;
	}

	public void setUser(JifenUser user) {
		this.user = user;
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
