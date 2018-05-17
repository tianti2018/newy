package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.DianpuForUser;
import com.zklc.weishangcheng.member.service.DianpuForUserService;

@Service
public class DianpuForUserServiceImpl extends BaseServiceImp<DianpuForUser, Integer> implements
		DianpuForUserService {

	@Override
	public DianpuForUser findByUserId(Integer userId) {
		List<DianpuForUser> dians = findByProperty("userId", userId);
		if(dians.size()>0){
			return dians.get(0);
		}
		return null;
	}

	
}
