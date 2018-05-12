package com.zklc.weishangcheng.member.service;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.Users;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;

public interface UseryService extends IBaseService<Usery, Integer> {

	Usery findbyUserId(Integer userId);
	
	Usery findbyJifenUser(Users user);
	
	Users findJifenUserByUsery(Usery usery);
	
	Usery findbyWxOpenId(String wxOpenId);
	
	Usery findbyUnionId(String unionId);
	
}
