package com.tw.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Namespace;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionChainResult;
import com.tw.web.dao.TAreaDao;
import com.tw.web.hibernate.persistent.TArea;


@SuppressWarnings("serial")
@ParentPackage("app-default")
@Namespace("")
@Results(
		{
			@Result(name="intcanyuhuodong", 			value="/WEB-INF/jsp/products/canyuhuodong.jsp"),
			
			@Result(name="gobackList4", type=ActionChainResult.class, 	value="user", params = {"method", "listAllTixian"})
		}
)
public class AreaAction extends ExtJSONActionSuport {

	
	private TAreaDao tAreaDao;
	private String areaCode;
	
	public void getTareaByCode(){    
 		JSONArray json = null;
		List<TArea> litTArea = tAreaDao.findEntityByPropertiName("parentCode", areaCode);
		if(litTArea !=null){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
  				json = JSONArray.fromObject(litTArea);
				response.getWriter().write(json.toString());
			}  catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public TAreaDao gettAreaDao() {
		return tAreaDao;
	}
	@Autowired
	public void settAreaDao(TAreaDao tAreaDao) {
		this.tAreaDao = tAreaDao;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
}
