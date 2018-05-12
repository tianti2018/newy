package com.tw.web.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.CardDAO;
import com.tw.web.dao.EggActivityUserDAO;
import com.tw.web.hibernate.persistent.Card;
import com.tw.web.hibernate.persistent.EggActivityUser;

@Repository(value="eggActivityUserDAO")
public class EggActivityUserDAOImpl extends CRUDBaseHibernateDAOImpl implements EggActivityUserDAO {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		return EggActivityUser.class;
	}

	@Override
	public EggActivityUser findNewData(Integer aid, Integer uid) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("activityId", aid));
		dc.add(Restrictions.eq("userId", uid));
		List<EggActivityUser> lit = getHibernateTemplate().findByCriteria(dc,0,1);		
		if (lit.size()!=0) {
			return  lit.get(0);
		}
		return null;
	}

	
}