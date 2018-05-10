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
import com.tw.web.dao.FunctionDAO;
import com.tw.web.dao.RoleDAO;
import com.tw.web.hibernate.persistent.Function;
import com.tw.web.hibernate.persistent.Role;
import com.tw.web.service.LoginService;

@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="initAdd", 			value="/WEB-INF/jsp/sys/addFunction.jsp"),
			@Result(name="initModify", 		value="/WEB-INF/jsp/sys/addFunction.jsp"),
			@Result(name="create", 				value="/WEB-INF/jsp/sys/addFunction.jsp"),
			@Result(name="listAll", 			value="/WEB-INF/jsp/sys/functionList.jsp"),
			@Result(name="goBackList", type=ActionChainResult.class, 	value="function", params = {"method", "listAll"})
		}
)
public class FunctionAction extends ExtJSONActionSuport {
	
	private LoginService loginService;
	private FunctionDAO functionDAO;
	private RoleDAO roleDAO;
	private int functionId;
	private String functionName;
	private String url;
	private String module;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public LoginService getLoginService() {
		return loginService;
	}		
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
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

	public String listAll () throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Integer count = functionDAO.cout_size_Commen(null, null);		
		
		// 修改的时候保存当前页
		if ((StringUtils.isNotEmpty(this.getCurrentPage())&&!"1".equals(this.getCurrentPage())) && StringUtils.isEmpty(this.getPagerMethod())) {
			this.setCurrentPage((Integer.parseInt(this.getCurrentPage())-1)+"");
			
			this.setPagerMethod("next");
		}
		this.setPager(getPagerService().getPager(this.getCurrentPage(), this.getPagerMethod(), count));
		
		this.setCurrentPage(String.valueOf(this.getPager().getCurrentPage()));
				
		List<Function> litPager =  functionDAO.findAllPagerList(null,null, null, this.getPager().getStartRow(), this.getPager().getPageSize(), "page");
		
		request.setAttribute("litPager", litPager);
		
		return "listAll";
		
	}
	
	public String initAdd () throws Exception {
		List<Role> listRole = roleDAO.findAll();
		
		ServletActionContext.getRequest().setAttribute("listAllRole", listRole);		
		return "initAdd";
	}
	
	public String initModify () throws Exception {
		
		Function oldFunction = (Function)functionDAO.findById(functionId);
		if (oldFunction == null) { 
			ServletActionContext.getRequest().setAttribute("message", "此功能不存在");
			
			return "listAll";
		}
		else {
			List<Role> listRole = roleDAO.findAll();
			
			ServletActionContext.getRequest().setAttribute("listAllRole", listRole);
			ServletActionContext.getRequest().setAttribute("function", oldFunction);
			
			return "initModify";
		}
				
	}
	
	public String create() throws Exception {
		
		Function oldFunction = functionDAO.findFunctionByFunctionName(functionName);
		
		if (oldFunction == null) {
			Function function = new Function ();
			
			function.setFunctionName(functionName);
			function.setUrl(url);
			function.setModule(module);
			functionDAO.create(function);
			
			ServletActionContext.getRequest().setAttribute("message", " 新增成功 ");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此功能名已经存在");
		}
				
		return "create";
	}
	
	public String delete() throws Exception {
		
		Function oldFunction =  (Function)functionDAO.findById(functionId);
		
		if (oldFunction != null) {
			functionDAO.delete(oldFunction);
			
			ServletActionContext.getRequest().setAttribute("message", "删除成功");
		}
		else {
			ServletActionContext.getRequest().setAttribute("message", "此功能不存在");
		}
		
		return "goBackList";
	}
	
	public String update() throws Exception  {
		
		Function oldFunction = (Function)functionDAO.findById(functionId);
		
		if (oldFunction != null) {
			
			oldFunction.setFunctionName(functionName);
			oldFunction.setUrl(url);
			oldFunction.setModule(module);
			
			functionDAO.update(oldFunction);		
			ServletActionContext.getRequest().setAttribute("message", " 修改成功 ");
		}
		else {
			
			ServletActionContext.getRequest().setAttribute("message", "此功能不存在");
		}
		
		return "goBackList";
	}
}
