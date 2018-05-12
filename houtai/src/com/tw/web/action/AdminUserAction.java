package com.tw.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.AdminUserDAO;
import com.tw.web.dao.RoleDAO;
import com.tw.web.hibernate.persistent.AdminUser;
import com.tw.web.hibernate.persistent.Role;
import com.tw.web.hibernate.persistent.User;
import com.tw.web.service.LoginService;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="initAddUser", 			value="/WEB-INF/jsp/sys/addUser.jsp"),
			@Result(name="initModifyUser", 		value="/WEB-INF/jsp/sys/addUser.jsp"),
			@Result(name="createUser", 							value="/WEB-INF/jsp/sys/addUser.jsp"),
			@Result(name="listAllUser", 			value="/WEB-INF/jsp/sys/userList1.jsp"),	
			
			@Result(name="initModifyPwd", 			value="/WEB-INF/jsp/sys/modifyPwd.jsp"),				
			@Result(name="modifyPwd", 			value="/WEB-INF/jsp/sys/modifyPwd.jsp"),			
			
			@Result(name="goBackList", type=ActionChainResult.class, 	value="adminUser", params = {"method", "listAllUser"})
		}
)
public class AdminUserAction extends ExtJSONActionSuport {
	
	private LoginService loginService;
	private AdminUserDAO adminUserDAO;
	private RoleDAO roleDAO;

	private int userId;
	private String loginName;
	private String password;
	private String userName;
	private String email;
	private int roleId;
	private String memo;
	private String newPassword;
	
	private String p_orgnizer;
	private String c_orgnizer;
	
	private String phone;
	private Integer money;
	private String account;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public LoginService getLoginService() {
		return loginService;
	}
	
	public AdminUserDAO getAdminUserDAO() {
		return adminUserDAO;
	}
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	@Autowired
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	@Autowired
	public void setAdminUserDAO(AdminUserDAO adminUserDAO) {
		this.adminUserDAO = adminUserDAO;
	}
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getP_orgnizer() {
		return p_orgnizer;
	}

	public void setP_orgnizer(String p_orgnizer) {
		this.p_orgnizer = p_orgnizer;
	}

	public String getC_orgnizer() {
		return c_orgnizer;
	}

	public void setC_orgnizer(String c_orgnizer) {
		this.c_orgnizer = c_orgnizer;
	}

	public String listAllUser () throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int countsize = adminUserDAO.cout_size_Commen(null, null);
				
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), countsize));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
						
		List<AdminUser> litPager = adminUserDAO.findAllPagerList(null,null, null, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAllUser";
		
	}
	
	public String initAddUser () throws Exception {
		List<Role> listRole = roleDAO.findAll();
		ServletActionContext.getRequest().setAttribute("listAllRole", listRole);
		ServletActionContext.getRequest().setAttribute("user", null);
		return "initAddUser";
	}
	
	public String initModifyUser () throws Exception {
		
		AdminUser oldUser = (AdminUser)adminUserDAO.findById(userId);
		if (oldUser == null) { 
			ServletActionContext.getRequest().setAttribute("message", "此用户不存在");
			
			return "listAllUser";
		}
		else {
			List<Role> listRole =  roleDAO.findAll();		
			ServletActionContext.getRequest().setAttribute("listAllRole", listRole);
			ServletActionContext.getRequest().setAttribute("user", oldUser);
			ServletActionContext.getRequest().setAttribute("phone", oldUser.getPhone());
			return "initModifyUser";
		}
				
	}
	
	public String createUser() throws Exception {
		
		AdminUser oldUser = (AdminUser)adminUserDAO.findUserByLoginName(loginName);
		Role role = (Role)roleDAO.findById(roleId);
		AdminUser user = new AdminUser ();
		if (oldUser == null) {
						
			user.setLoginName(loginName);
			user.setPassWord(password);
			user.setMemo(memo);
			user.setRole(role);
			user.setPhone(phone);
			user.setUserName(userName);
			
			adminUserDAO.create(user);
			ServletActionContext.getRequest().setAttribute("message", " 新增成功 ");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此用户名已经存在");
					
		}
						
		List<Role> listRole = roleDAO.findAll();			
		ServletActionContext.getRequest().setAttribute("listAllRole", listRole);
				
		return "createUser";
	}
	
	public String deleteUser() throws Exception {
		AdminUser user = (AdminUser)adminUserDAO.findById(userId);
		
		if (user != null) {
			adminUserDAO.delete(user);
			ServletActionContext.getRequest().setAttribute("message", "删除成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此用户不存在");
		}
		
		return "goBackList";
	}
	
	public String updateUser() throws Exception  {
		
		AdminUser user = (AdminUser)adminUserDAO.findById(userId);
		Role role = (Role)roleDAO.findById(roleId);
		
		AdminUser checkUser = (AdminUser)adminUserDAO.findUserByLoginName(loginName);
		if (checkUser != null && (!checkUser.getLoginName().equals(user.getLoginName()))) {
			ServletActionContext.getRequest().setAttribute("message", "此用户名已经存在");
						
			List<Role> listRole = roleDAO.findAll();			
			ServletActionContext.getRequest().setAttribute("listAllRole", listRole);
			ServletActionContext.getRequest().setAttribute("user", user);
			
			return "initModifyUser";
		}
		
		if (user != null) {
			user.setLoginName(loginName);
								
			if (StringUtils.isNotEmpty(password)) {
				user.setPassWord(password);
			}
			
			user.setEmail(email);
			user.setMemo(memo);
			user.setRole(role);
			user.setUserName(userName);	
			user.setPhone(phone);
			
			adminUserDAO.update(user);
			ServletActionContext.getRequest().setAttribute("message", " 修改成功 ");
		}
		else {
			
			ServletActionContext.getRequest().setAttribute("message", "此用户不存在");
		}
		
		return "goBackList";
	}
	
	public String initModifyPwd() throws Exception {			
		return "initModifyPwd";
	}
	
	public String modifyPwd() throws Exception  {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		if (this.password.equals(user.getPassWord())) {
			user.setPassWord(this.newPassword);
			
			loginService.updateUser(user);
			
			ServletActionContext.getRequest().setAttribute("message", " 修改密码成功 ");			
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", " 原密码不正確");					
		}
		
		return "modifyPwd";
	}
		
}
