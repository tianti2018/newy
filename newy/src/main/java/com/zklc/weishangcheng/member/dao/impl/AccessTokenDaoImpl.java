package com.zklc.weishangcheng.member.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import com.zklc.framework.dao.impl.EntityDaoImpl;
import com.zklc.weishangcheng.member.dao.AccessTokenDao;
import com.zklc.weishangcheng.member.hibernate.persistent.AccessToken;
@Repository
public class AccessTokenDaoImpl extends EntityDaoImpl <AccessToken, Integer> implements AccessTokenDao {

	@Override
	public AccessToken findByAccessTokenTopnewOne() {
		DetachedCriteria dc = DetachedCriteria.forClass(AccessToken.class);
		dc.addOrder(Order.desc("createDate"));
		List<AccessToken> list = getHibernateTemplate().findByCriteria(dc,0,1);
		if (list.size()!=0) {
			return list.get(0);
		}
		return null;
	}
}
