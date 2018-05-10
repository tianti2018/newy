package com.tw.web.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.RoleFunctionDAO;
import com.tw.web.hibernate.persistent.RoleFunction;

@Repository
public class RoleFunctionDAOImpl extends CRUDBaseHibernateDAOImpl implements RoleFunctionDAO {

	@SuppressWarnings("unchecked")
	@Override
	protected Class getPojoClass() { 
		
		return RoleFunction.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleFunction> findFunctionByRoleId(int roleId) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("roleId", roleId));
		List<RoleFunction> rfl = getHibernateTemplate().findByCriteria(dc);
		if (null!=rfl) {
			return rfl;
		}
		return null;
	}

}
