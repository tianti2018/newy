package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.hibernate.persistent.DianForUser;
import com.zklc.weishangcheng.member.service.DianForUserService;

@Service
public class DianForUserServiceImpl extends BaseServiceImp<DianForUser, Integer> implements
		DianForUserService {

	@Override
	public DianForUser findByUserId(Integer userId) {
		List<DianForUser> dians = findByProperty("userId", userId);
		if(dians.size()>0){
			return dians.get(0);
		}
		return null;
	}

	
}
