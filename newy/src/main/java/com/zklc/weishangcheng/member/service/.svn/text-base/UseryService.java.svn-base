package com.zklc.weishangcheng.member.service;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;

public interface UseryService extends IBaseService<Usery, Integer> {

	Usery findbyUserId(Integer userId);
	
	Usery findbyJifenUser(JifenUser user);
	
	JifenUser findJifenUserByUsery(Usery usery);
	
	Usery findbyWxOpenId(String wxOpenId);
	
	Usery findbyUnionId(String unionId);
	
}
