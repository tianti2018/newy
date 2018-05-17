package com.zklc.weishangcheng.member.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.CompanyInfo;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.CompanyInfoService;
import com.zklc.weishangcheng.member.service.UserService;
import com.zklc.weishangcheng.member.service.UseryService;
import com.zklc.weixin.util.WeixinUtil;

@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/companyInfo")
@Action(value = "companyInfoAction")
@Results({
	@Result(name = "reditPage",type = "redirect", location = "/companyInfo/companyInfoAction!editPage.action"),
	@Result(name = "orderAddressPage", location = "/WEB-INF/jsp/orderAddress.jsp"),
	@Result(name = "editPage", location = "/WEB-INF/jsp/zhuce.jsp"),
	@Result(name = "ajaxResult", type = "json", params = {"message","message"}),
	@Result(name = "rEditPage", type = "redirect", location = "/user/userAction!gotoPersonalCenter.action")
})
public class CompanyInfoAction extends BaseAction {

	@Autowired
	private CompanyInfoService companyInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private UseryService useryService;
	
	private Users user;
	
	private Usery usery;
	
	private CompanyInfo companyInfo;
	
	private String wxOpenid;
	
	private String code;
	
	private Integer userId;
	
	public String saveOrupdate(){
		userVo = getSessionUser();
		user = userVo.getUser();
		if(user==null){
			return"timeOut";
		}
		if(companyInfo.getId()==null){
			companyInfo.setCreateDate(new Date());
			companyInfo.setUserId(user.getUserId());
			companyInfoService.save(companyInfo);
		}else{
			companyInfoService.update(companyInfo);
		}
		return "reditPage";
	}
	
	public String editPage(){
		userVo = getSessionUser();
		user = userVo.getUser();
//		user = userService.findById(3480);
//		request.getSession().setAttribute("loginUser",user);
		if(user==null){
			return"timeOut";
		}
		List<CompanyInfo> companyInfos = companyInfoService.findByProperty("userId", user.getUserId());
		if(companyInfos.size()>0){
			companyInfo = companyInfos.get(0);
		}
		return "editPage";
	}
	
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Usery getUsery() {
		return usery;
	}

	public void setUsery(Usery usery) {
		this.usery = usery;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	
	
	
	
}
