package com.zklc.weishangcheng.member.service;

import javax.servlet.http.HttpServletRequest;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;

public interface CoreService extends IBaseService<Users, Integer> {
	public  String processRequest(HttpServletRequest request);
}