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
import com.tw.web.dao.EggPrizeDAO;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.EggPrize;

@Repository(value="eggPrizeDAO")
public class EggPrizeDAOImpl extends CRUDBaseHibernateDAOImpl implements EggPrizeDAO {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		return EggPrize.class;
	}

	@Override
	public List findHavLists(Integer aid) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("activityId", aid));
		dc.add(Restrictions.eq("status", "1"));
		dc.addOrder(Order.asc("winRate"));
		List<EggPrize> lit = getHibernateTemplate().findByCriteria(dc);
		return lit;
	}

}