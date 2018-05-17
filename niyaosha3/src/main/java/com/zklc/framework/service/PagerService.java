package com.zklc.framework.service;

import com.zklc.framework.hibernate.persistent.Pager;



public interface PagerService {
	@SuppressWarnings("unchecked")
	public Pager getPager(String currentPage,String pagerMethod,int totalRows);
}
