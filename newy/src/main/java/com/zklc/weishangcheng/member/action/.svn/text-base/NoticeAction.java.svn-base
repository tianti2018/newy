package com.zklc.weishangcheng.member.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Notice;
import com.zklc.weishangcheng.member.service.JifenUserService;
import com.zklc.weishangcheng.member.service.NoticeService;
import com.zklc.weixin.util.WeixinUtil;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/notice")
@Action(value="noticeAction")
@Results({@Result(name="noticeList",location="/WEB-INF/jsp/noticeList.jsp")})
public class NoticeAction extends BaseAction {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private JifenUserService userService;
	
	private JifenUser user;
	
	private Integer status;
	private Integer pageNum;
	public String code;
	public String wxOpenid;
	
	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public JifenUserService getUserService() {
		return userService;
	}

	public void setUserService(JifenUserService userService) {
		this.userService = userService;
	}

	public JifenUser getUser() {
		return user;
	}

	public void setUser(JifenUser user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
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
	 * 公告列表
	 */
	public String noticeList(){
		//获取用户信息
		user=getSessionUser();
		if(user==null){
			return "timeOut";
		}
		List<Notice> notices=noticeService.getAll(pageNum);
		request.setAttribute("notices", notices);
		System.out.println("notices.size="+notices.size());
		request.setAttribute("length", notices.size());
		return "noticeList";
	}
	
	public String ajaxNoticeList(){
		//获取用户信息
		user=getSessionUser();
		if(user==null){
			return "timeOut";
		}
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		List<Notice> mo=noticeService.getAll(pageNum);
		System.out.println("mo.size="+mo.size());
		response=ServletActionContext.getResponse();
		/*****************************组装json*******************************/
		if(mo!=null&&mo.size()>0){
			jsonMap.put("success", true);
			jsonMap.put("children", mo);
		}else{
			jsonMap.put("success", false);
			jsonMap.put("length", mo.size());
		}
		JSONObject jsonObject=JSONObject.fromObject(jsonMap);
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询最近的一条公告
	 */
	public String findLastNotice(){
		//获取用户信息
		user=getSessionUser();
		if(user==null){
			return "timeOut";
		}
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		String mo=noticeService.findLastNotice();
		response=ServletActionContext.getResponse();
		/*****************************组装json*******************************/
		if(mo!=null){
			jsonMap.put("success", true);
			jsonMap.put("noticeName", mo);
		}else{
			jsonMap.put("success", false);
		}
		JSONObject jsonObject=JSONObject.fromObject(jsonMap);
		try {
			response.getWriter().print(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private JifenUser getSessionUser(){
		
		 user = (JifenUser) request.getSession().getAttribute("loginUser");
		 if(user==null){
			 code = request.getParameter("code");
			 wxOpenid=(String) request.getSession().getAttribute("wxOpenid");
			
			 if(code!=null){
				 if(wxOpenid==null){
					wxOpenid = WeixinUtil.code2openid(code);
					request.getSession().setAttribute("wxOpenid",wxOpenid);
				 }
			 }
			 if(wxOpenid!=null){
					List<JifenUser> userList = userService.findByProperty("wxOpenid", wxOpenid);
					if (userList != null && userList.size() > 0) {
						user = userList.get(0);
						request.getSession().setAttribute("loginUser",user);
					}else{
						user = userService.findUserByOpenId(wxOpenid);
					}
			 }
		 }
		return  user;
	}


}
