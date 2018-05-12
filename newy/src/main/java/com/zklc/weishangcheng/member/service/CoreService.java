package com.zklc.weishangcheng.member.service;

import javax.servlet.http.HttpServletRequest;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;

public interface CoreService extends IBaseService<JifenUser, Integer> {
	public  String processRequest(HttpServletRequest request);
	
	public JifenUser findByOpenid(String openid);
}