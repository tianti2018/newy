package com.tw.web.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tw.web.dao.ApplyReturnPurchaseDao;
import com.tw.web.hibernate.persistent.ApplyReturnPurchase;

@Repository
public class ApplyReturnPurchaseDaoImpl extends CRUDBaseHibernateDAOImpl
		implements ApplyReturnPurchaseDao {

	@Override
	protected Class getPojoClass() {
		return ApplyReturnPurchase.class;
	}

	@Override
	public List<ApplyReturnPurchase> findByHql(String hql) {
		return this.getSession().createQuery(hql).list();
	}

}
