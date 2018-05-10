package com.tw.web.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.RecordDAO;
import com.tw.web.dao.RepeatDAO;

import com.tw.web.hibernate.persistent.Record;
import com.tw.web.hibernate.persistent.Repeat;

@Repository
public class RepeatDAOImpl extends CRUDBaseHibernateDAOImpl implements RepeatDAO {

	@SuppressWarnings("unchecked")
	@Override
	protected Class getPojoClass() {
		
		return Repeat.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findRepeatByCrrentYM(Integer userId, String yyyyMM) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.add(Restrictions.eq("createDate", yyyyMM));
				
		List<Repeat> lpt = getHibernateTemplate().findByCriteria(dc);
		
		if (lpt.size()!=0) {
			return true;
		}
		
		return false;
	}

	@Override
	public Double findTotalMoney(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		dc.setProjection(Projections.sum("repeatMoney"));
		
		List<Double> money = getHibernateTemplate().findByCriteria(dc,0,1);
		if (null!=money) {
			return null==money.get(0)?0:money.get(0);
		}
		return 0.0;
	}	
}
