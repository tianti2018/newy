package com.tw.web.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.tw.web.hibernate.persistent.Pager;
import com.tw.web.service.PagerService;

public class ExtJSONActionSuport extends ActionSupport {
	
	private PagerService pagerService;
	
	private Pager pager;
	

	protected String currentPage;
	protected String pagerMethod;	
	
	private String jsonString = "";

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String jsonExecute() throws Exception {
		return super.execute();
	}	
	
	public PagerService getPagerService() {
		return pagerService;
	}
	
	@Autowired
	public void setPagerService(PagerService pagerService) {
		this.pagerService = pagerService;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPagerMethod() {
		return pagerMethod;
	}

	public void setPagerMethod(String pagerMethod) {
		this.pagerMethod = pagerMethod;
	}
	
}
