
package com.tw.web.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.tw.web.dao.AdminUserDAO;
import com.tw.web.dao.FhRecordDAO;
import com.tw.web.dao.UserDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.User;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Results(
		{
			@Result(name="main", 		value="/WEB-INF/jsp/main/main.jsp"),
			@Result(name="top", 		value="/WEB-INF/jsp/main/top.jsp"),
			@Result(name="middel", 		value="/WEB-INF/jsp/main/middel.jsp"),
			@Result(name="center", 	value="/WEB-INF/jsp/main/center.jsp"),
			@Result(name="left", 		value="/WEB-INF/jsp/main/left.jsp"),
			@Result(name="right", 		value="/WEB-INF/jsp/main/right.jsp"),
			@Result(name="bottom", 		value="/WEB-INF/jsp/main/bottom.jsp")
		}
)
public class MainAction extends ExtJSONActionSuport {
	
	private FhRecordDAO fhRecordDAO;
	private String loginPage;
	private AdminUserDAO adminUserDAO;
	
	public String getLoginPage() {
		return loginPage;
	}
	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
	public FhRecordDAO getFhRecordDAO() {
		return fhRecordDAO;
	}
	@Autowired
	public void setFhRecordDAO(FhRecordDAO fhRecordDAO) {
		this.fhRecordDAO = fhRecordDAO;
	}

	public AdminUserDAO getAdminUserDAO() {
		return adminUserDAO;
	}
	@Autowired
	public void setAdminUserDAO(AdminUserDAO adminUserDAO) {
		this.adminUserDAO = adminUserDAO;
	}
	public String goToMain () {
		return "main";
	}
	
	public String goToTop () {
		ServletActionContext.getRequest().setAttribute("loginPage", loginPage);
		return "top";
	}
	
	public String goToCenter () {
		ServletActionContext.getRequest().setAttribute("loginPage", loginPage);
		return "center";
	}
	
	public String goToMiddel () {
		ServletActionContext.getRequest().setAttribute("loginPage", loginPage);
		return "middel";
	}
	
	public String goToLeft () {
		
		Object user = null;
		
		user = ServletActionContext.getRequest().getSession().getAttribute("user");
		if (user instanceof User) {
			User user1 = (User)user; 
			
			ServletActionContext.getRequest().setAttribute("users", user1);
		}
		else if(user instanceof AdminUser) {
			AdminUser user1 = (AdminUser)user;
			ServletActionContext.getRequest().setAttribute("users", user1);
		}
		
		ServletActionContext.getRequest().setAttribute("loginPage", loginPage);
		
		return "left";
	}
	
	public String goToRight () {
		ServletActionContext.getRequest().setAttribute("loginPage", loginPage);
		return "right";
	}
	
	public String goToBottom () {
		return "bottom";
	}
}
