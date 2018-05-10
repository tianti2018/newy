package com.tw.web.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.FunctionDAO;
import com.tw.web.hibernate.persistent.Function;

@Repository
public class FunctionDAOImpl extends CRUDBaseHibernateDAOImpl implements FunctionDAO {

	@SuppressWarnings("unchecked")
	@Override
	protected Class getPojoClass() { 
		
		return Function.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Function findFunctionByFunctionName(String functionName) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("functionName", functionName));
		
		List<Function> functionLit = (List<Function>)getHibernateTemplate().findByCriteria(dc);
		
		if ( functionLit.size() != 0) {
			return functionLit.get(0);
		}
		return null;
	}

}
