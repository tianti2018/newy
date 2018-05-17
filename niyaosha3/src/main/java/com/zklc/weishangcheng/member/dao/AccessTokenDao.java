package com.zklc.weishangcheng.member.dao;
import com.zklc.weishangcheng.member.hibernate.persistent.AccessToken;
import com.zklc.framework.dao.EntityDao;
public interface AccessTokenDao extends EntityDao<AccessToken, Integer> {
	public AccessToken findByAccessTokenTopnewOne();
}