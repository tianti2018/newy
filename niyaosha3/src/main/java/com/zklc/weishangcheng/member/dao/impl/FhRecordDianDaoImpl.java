package com.zklc.weishangcheng.member.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.zklc.framework.dao.impl.EntityDaoImpl;
import com.zklc.weishangcheng.member.dao.FhRecordDianDao;
import com.zklc.weishangcheng.member.hibernate.persistent.FhRecordDian;

@SuppressWarnings("unchecked")
@Repository
public class FhRecordDianDaoImpl extends EntityDaoImpl<FhRecordDian, Integer> implements
		FhRecordDianDao {

	@Override
	public int findCountFhRecordByLevel(Integer userId, int level) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("flag", 2));
		dc.add(Restrictions.eq("level", level));
		dc.setProjection(Projections.count("userId"));
		
		List list = getHibernateTemplate().findByCriteria(dc);
		if (null!=list&&list.size()!=0) {
			return (null!=list.get(0))?Integer.parseInt(list.get(0).toString()):0;
		}
		return 0;
	}

	@Override
	public FhRecordDian findFhRecordBytoUserId(Integer puserId,
			Integer fromUserId, double amount, Integer flag, Integer ordersId,
			int level) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", puserId));
		dc.add(Restrictions.eq("flag", flag));
		dc.add(Restrictions.eq("fromUserId", fromUserId));
		dc.add(Restrictions.eq("fhmoney", amount));
		dc.add(Restrictions.eq("dianzhuLevel", level));
//		dc.add(Restrictions.eq("ordersId", ordersId));
		
		List list = getHibernateTemplate().findByCriteria(dc);
		if (null!=list&&list.size()!=0) {
			return (FhRecordDian) list.get(0);
		}
		return null;
	}


}
