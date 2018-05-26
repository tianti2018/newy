package com.zklc.weishangcheng.member.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.zklc.framework.action.BaseAction;
import com.zklc.weishangcheng.member.hibernate.persistent.TArea;
import com.zklc.weishangcheng.member.service.TareaService;


@SuppressWarnings("serial")
@ParentPackage("json")
@Namespace("/tarea")
@Action(value = "tareaAction")
@Results(
		{
			@Result(name="intcanyuhuodong", 			value="/WEB-INF/jsp/products/canyuhuodong.jsp"),
			
			@Result(name="gobackList4", type=ActionChainResult.class, 	value="user", params = {"method", "listAllTixian"})
		}
)
public class TareaAction extends BaseAction {

	
	@Autowired
	private TareaService tareaService;
	private String areaCode;
	
	public void getTareaByCode(){    
		List<TArea> litTArea = tareaService.findByProperty("parentCode", areaCode);
		if(litTArea !=null){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				jsonArrayOut(litTArea);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
