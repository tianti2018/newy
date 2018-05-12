package com.tw.web.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.CardDAO;
import com.tw.web.dao.EggPrizeUserDAO;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.EggPrizeUser;

@Repository(value="eggPrizeUserDAO")
public class EggPrizeUserDAOImpl extends CRUDBaseHibernateDAOImpl implements EggPrizeUserDAO {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		return EggPrizeUser.class;
	}




	@Override
	public Card findCardByUserId(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("cardStatus", "0"));
		dc.add(Restrictions.eq("userId", userId));
		List<Card> lit = getHibernateTemplate().findByCriteria(dc,0,1);		
		if (lit.size()!=0) {
			return  lit.get(0);
		}
		return null;
	}

	@Override
	public List findListByAidAndUid(Integer aid, Integer uid) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("activityId", aid));
		if(uid!=null)
			dc.add(Restrictions.eq("userId", uid));
		dc.addOrder(Order.desc("createTime"));
		List<Integer> lit = getHibernateTemplate().findByCriteria(dc);
		return lit;
	}




	@Override
	public List topNumList(Integer maxNum) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.addOrder(Order.desc("createTime"));
		List<Integer> lit = getHibernateTemplate().findByCriteria(dc, 1, maxNum);
		return lit;
	}
	
}