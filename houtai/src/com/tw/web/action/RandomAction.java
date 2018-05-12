package com.tw.web.action;

import java.io.ByteArrayInputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tw.web.util.RandomNumUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("RandomAction")
public class RandomAction extends ExtJSONActionSuport {

	private ByteArrayInputStream inputStream;
	private String random;
	
	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	
	public String ajaxRandom() throws Exception {
		
		this.setJsonString("{success:true,random:+'"+this.random+"'}");
		
		return "joson"; 
	}
	
	public String execute() throws Exception {
		
		RandomNumUtil rdnu=RandomNumUtil.Instance();   
		
		// 取得带有随机字符串的图片
		this.setInputStream(rdnu.getImage());
		
		// 取得随机字符串放入HttpSession
		//ActionContext.getContext().getSession().put("random", rdnu.getString());	
		ServletActionContext.getRequest().getSession().removeAttribute("random");
		ServletActionContext.getRequest().getSession().setAttribute("random", rdnu.getString());
		
		this.random = rdnu.getString();
		//this.setJsonString("{success:true,message:'审核成功!!!'}");	
		
		return SUCCESS;
	}

	

}
