package com.tw.web.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.UserOrderDAO;
import com.tw.web.hibernate.persistent.UserOrder;

@Repository
public class UserOrderDAOImpl extends CRUDBaseHibernateDAOImpl implements UserOrderDAO {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		return UserOrder.class;
	}

	@Override
	public UserOrder findUserOrderByAppcenterIdAndorderId(String flagorg,
			Integer orderOne) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("flagorg", flagorg));
		dc.add(Restrictions.eq("orderOne", BigInteger.valueOf(orderOne)));
		List<UserOrder> list = getHibernateTemplate().findByCriteria(dc,0,1);
		if (list!=null&&list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserOrder findUserOrderByUserId(Integer userId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("userId", userId));
		List<UserOrder> list = getHibernateTemplate().findByCriteria(dc,0,1);
		if (list!=null&&list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<UserOrder> findByHql(String hql) {
		return this.getSession().createQuery(hql).list();
	}

}
