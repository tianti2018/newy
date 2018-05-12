package com.tw.web.dao.impl;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.RelationDAO;
import com.tw.web.hibernate.persistent.Relation;

@Repository
public class RelationDAOImpl extends CRUDBaseHibernateDAOImpl implements RelationDAO {

	@SuppressWarnings("unchecked")
	@Override
	protected Class getPojoClass() {
		
		return Relation.class;
	}

	@Override
	public com.tw.web.hibernate.persistent.Relation findRelationByUserId(
			Integer recommend) throws Exception {
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("recommend", recommend));
		dc.addOrder(Order.desc("createDate"));
		
		List<Relation> lit = getHibernateTemplate().findByCriteria(dc);
		if (lit.size()!=0) {
			return (Relation)lit.get(0);
		}
		return null;
	}

}
