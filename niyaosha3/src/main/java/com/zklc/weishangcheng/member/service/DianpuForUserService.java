package com.zklc.weishangcheng.member.service;

import com.zklc.framework.service.IBaseService;
import com.zklc.weishangcheng.member.hibernate.persistent.DianpuForUser;

public interface DianpuForUserService extends IBaseService<DianpuForUser, Integer> {

	public DianpuForUser findByUserId(Integer userId);
	
}
