package com.zklc.weishangcheng.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zklc.framework.service.impl.BaseServiceImp;
import com.zklc.weishangcheng.member.dao.JifenUserDao;
import com.zklc.weishangcheng.member.hibernate.persistent.JifenUser;
import com.zklc.weishangcheng.member.hibernate.persistent.Usery;
import com.zklc.weishangcheng.member.service.UseryService;

@Service
public class UseryServiceImpl extends BaseServiceImp<Usery, Integer> implements
		UseryService {

	@Autowired
	private JifenUserDao jifenUserDao;
	
	@Override
	public Usery findbyUserId(Integer userId) {
		List<Usery> userys = findByProperty("userId", userId);
		if(userys.size()>0){
			return userys.get(0);
		}
		return null;
	}

	@Override
	public Usery findbyJifenUser(JifenUser user) {
		return findbyUserId(user.getUserId());
	}

	@Override
	public JifenUser findJifenUserByUsery(Usery usery) {
		return jifenUserDao.findById(usery.getUserId());
	}

	@Override
	public Usery findbyWxOpenId(String wxOpenId) {
		List<Usery> userys = findByProperty("wxOpenid", wxOpenId);
		if(userys.size()>0){
			return userys.get(0);
		}
		return null;
	}

	@Override
	public Usery findbyUnionId(String unionId) {
		List<Usery> userys = findByProperty("unionid", unionId);
		if(userys.size()>0){
			return userys.get(0);
		}
		return null;
	}


}
