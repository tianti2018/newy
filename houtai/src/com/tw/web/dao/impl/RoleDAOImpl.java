package com.tw.web.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.RoleDAO;
import com.tw.web.hibernate.persistent.Role;

@Repository
public class RoleDAOImpl extends CRUDBaseHibernateDAOImpl implements RoleDAO {

	@SuppressWarnings("unchecked")
	@Override
	protected Class getPojoClass() { 
		
		return Role.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Role findRoleByRoleName(String roleName) {
		DetachedCriteria dc = DetachedCriteria.forClass(getPojoClass());
		dc.add(Restrictions.eq("roleName", roleName));
		
		List<Role> roleLit = (List<Role>)getHibernateTemplate().findByCriteria(dc);
		
		if ( roleLit.size() != 0) {
			return roleLit.get(0);
		}
		return null;
	}

}
