package com.tw.web.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.AdminUserDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.service.LoginService;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="success", 		value="/WEB-INF/jsp/main/main.jsp"),
			@Result(name="error", 		value="/login2.jsp"),	
			
			@Result(name="adminsuccess", 		value="/WEB-INF/jsp/main/main.jsp"),
			@Result(name="adminerror", 		value="/login.jsp"),
			@Result(name="mobilesuccess", type=ActionChainResult.class, 	value="orders", params = {"method", "ordersList"}),
		}
)
public class LoginAction extends ExtJSONActionSuport {
	
	private LoginService loginService;
	private AdminUserDAO adminUserDAO;
	private String loginName;
	private String password;
	private String random;
	private String loginPage;
	private String shebei;
	
	public String doLogin () throws Exception { //会员登录
						
		int intFlag = this.loginService.checkUserExistOrNo(loginName, password);
			
		// 该登录用户不存在
		if (intFlag == -1) {
			ServletActionContext.getRequest().setAttribute("message", "-1");
			return ERROR;
		}
		
		// 登录用户名或密码错误
		else if (intFlag == 12) {
			ServletActionContext.getRequest().setAttribute("message", "12");
			return ERROR;
		}
		else if (intFlag == 11) {
			User user = loginService.findUserByLoginName(loginName);
//			if ("1".equals(user.getBlock())) {
//				ServletActionContext.getRequest().setAttribute("message", "您的用户编号已被管理员锁定");
//				return ERROR;
//			}
			String sessionRandom = (String)ServletActionContext.getRequest().getSession().getAttribute("random");
			if (this.random.equals(sessionRandom)) {
				
				ServletActionContext.getRequest().setAttribute("message", "登录成功！");
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("user", user);
				//((ActionContext) session).put(CheckLoginInterceptor.USER_SESSION_KEY, user);  
				ServletActionContext.getRequest().setAttribute("loginPage", "1");		
				session.setAttribute("shebei", shebei);
				if(shebei.equals("mobile")){
					return "mobilesuccess";
				}
				return SUCCESS;
			}
			else {
				ServletActionContext.getRequest().setAttribute("message", "验证码错误");
				return ERROR;
			}				
		}
		
		
		return ERROR;
	}
	
	public String doAdminLogin () throws Exception { //后台人员登录
		
		int intFlag = adminUserDAO.checkUserExistOrNo(loginName, password);
			
		// 该登录用户不存在
		if (intFlag == -1) {
			
			ServletActionContext.getRequest().setAttribute("message", "-1");
			
			return "adminerror";
		}
		
		// 登录用户名或密码错误
		else if (intFlag == 12) {
			
			ServletActionContext.getRequest().setAttribute("message", "12");
			return "adminerror";
		}
		else if (intFlag == 11) {
			
			String sessionRandom = (String)ServletActionContext.getRequest().getSession().getAttribute("random");
			if (this.random.equals(sessionRandom)) {
				ServletActionContext.getRequest().setAttribute("message", "登录成功！");
				HttpSession session = ServletActionContext.getRequest().getSession();
				
				AdminUser user = adminUserDAO.findUserByLoginName(loginName);
				session.setAttribute("user", user);
				//((ActionContext) session).put(CheckLoginInterceptor.USER_SESSION_KEY, user);  
				ServletActionContext.getRequest().setAttribute("loginPage", "2");	
				session.setAttribute("shebei", shebei);
				if(shebei.equals("mobile")){
					return "mobilesuccess";
				}
				return "adminsuccess";
			}
			else {
				ServletActionContext.getRequest().setAttribute("message", "验证码错误");
				return "adminerror";
			}				
		}
		
		
		return "adminerror";
	}
	
	public String loginOut() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession(false);
				
		if (session != null) {		
			session.invalidate();			
		}
		
		return "1".equals(loginPage)?ERROR:"adminerror";
		
	}
	public String getShebei() {
		return shebei;
	}
	public void setShebei(String shebei) {
		this.shebei = shebei;
	}
	public AdminUserDAO getAdminUserDAO() {
		return adminUserDAO;
	}
	@Autowired
	public void setAdminUserDAO(AdminUserDAO adminUserDAO) {
		this.adminUserDAO = adminUserDAO;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public LoginService getLoginService() {
		return loginService;
	}
	
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public String getLoginPage() {
		return loginPage;
	}
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
