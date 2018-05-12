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
import com.tw.web.dao.RoleDAO;
import com.tw.web.hibernate.persistent.Function;
import com.tw.web.hibernate.persistent.Role;
import com.tw.web.service.LoginService;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="initAdd", 			value="/WEB-INF/jsp/sys/addRole.jsp"),
			@Result(name="initModify", 		value="/WEB-INF/jsp/sys/addRole.jsp"),
			@Result(name="create", 				value="/WEB-INF/jsp/sys/addRole.jsp"),
			@Result(name="listAll", 			value="/WEB-INF/jsp/sys/roleList.jsp"),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="role", params = {"method", "listAll"})
		}
)
public class RoleAction extends ExtJSONActionSuport {
	
	private LoginService loginService;
	private RoleDAO roleDAO;
	private int roleId;
	private String roleName;
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public LoginService getLoginService() {
		return loginService;
	}
	
	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	@Autowired
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String listAll () throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Integer count = roleDAO.cout_size_Commen(null, null);
				
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
				
		List<Role> litPager = roleDAO.findAllPagerList(null,null, null, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		request.setAttribute("litPager", litPager);
		
		return "listAll";
		
	}
	
	public String initAdd () throws Exception {
		
		return "initAdd";
	}
	
	public String initModify () throws Exception {
		
		Role role = (Role)roleDAO.findById(roleId);
		if (role == null) { 
			ServletActionContext.getRequest().setAttribute("message", "此角色不存在");
			
			return "listAll";
		}
		else {
			
			ServletActionContext.getRequest().setAttribute("role", role);
			
			return "initModify";
		}
				
	}
	
	public String create() throws Exception {
		
		Role role = roleDAO.findRoleByRoleName(roleName);
		
		if (role == null) {
			Role newRole = new Role ();
			
			newRole.setRoleName(roleName);
			
			roleDAO.create(newRole);
			
			ServletActionContext.getRequest().setAttribute("message", " 新增成功 ");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此角色名已经存在");
		}
				
		return "create";
	}
	
	public String delete() throws Exception {
		
		Role role = (Role)roleDAO.findById(roleId);
		if (role != null) {
			roleDAO.delete(role);
			ServletActionContext.getRequest().setAttribute("message", "删除成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此角色不存在");
		}
		
		return "goBackList";
	}
	
	public String update() throws Exception  {
		
		Role role = (Role)roleDAO.findById(roleId);
		if (role != null) {
			
			role.setRoleName(roleName);
			roleDAO.update(role);
			ServletActionContext.getRequest().setAttribute("message", " 修改成功 ");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此角色不存在");
		}
		
		return "goBackList";
	}
}
