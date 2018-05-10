package com.tw.web.action;

import java.util.ArrayList;
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
import com.tw.web.dao.FunctionDAO;
import com.tw.web.dao.RoleDAO;
import com.tw.web.dao.RoleFunctionDAO;
import com.tw.web.hibernate.persistent.Function;
import com.tw.web.hibernate.persistent.Role;
import com.tw.web.hibernate.persistent.RoleFunction;
import com.tw.web.service.LoginService;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="listAll", 		value="/WEB-INF/jsp/sys/authorization.jsp"),
			@Result(name="error", 		value="/login.jsp"),
			@Result(name="create", type=ActionChainResult.class, 	value="roleFunction", params = {"method", "listAll"})
		}
)
public class RoleFunctionAction extends ExtJSONActionSuport {
	private LoginService loginService;
	private FunctionDAO functionDAO;
	private RoleDAO roleDAO;
	private RoleFunctionDAO roleFunctionDAO;
	
	private int roleId;
	private List<Integer> functionId;
	private List<Function> litPager;
	

	public List<Function> getLitPager() {
		return litPager;
	}

	public void setLitPager(List<Function> litPager) {
		this.litPager = litPager;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getFunctionId() {
		return functionId;
	}

	public void setFunctionId(List<Integer> functionId) {
		this.functionId = functionId;
	}

	public LoginService getLoginService() {
		return loginService;
	}
	
	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public FunctionDAO getFunctionDAO() {
		return functionDAO;
	}
	@Autowired
	public void setFunctionDAO(FunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}
	@Autowired
	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public RoleFunctionDAO getRoleFunctionDAO() {
		return roleFunctionDAO;
	}
	@Autowired
	public void setRoleFunctionDAO(RoleFunctionDAO roleFunctionDAO) {
		this.roleFunctionDAO = roleFunctionDAO;
	}

	public String listAll () throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<Role> listRole = roleDAO.findAll();
		
		List<Function> listFunction = functionDAO.findAll();
		
		if (0!=roleId&&StringUtils.isNotEmpty(roleId+"")) {
			List<RoleFunction> litF = roleFunctionDAO.findFunctionByRoleId(roleId);
			
			List<Integer> litFOne = new ArrayList<Integer>();
			for (int i=0; i<litF.size(); i++) {
				Function function = litF.get(i).getFunction();
				
				litFOne.add(function.getFunctionId());
			}
			
			request.setAttribute("had", litFOne);
		}
		
		this.setLitPager(listFunction);
		request.setAttribute("litPager", listFunction);
		request.setAttribute("listAllRole", listRole);
		return "listAll";
	}
	
	public String create () throws Exception {
						
		loginService.openerRF(roleId, functionId);
		
		return "create";
	}
	
}
