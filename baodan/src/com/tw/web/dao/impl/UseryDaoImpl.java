package com.tw.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.tw.web.dao.UseryDao;
import com.tw.web.hibernate.persistent.Usery;

@Repository
public class UseryDaoImpl extends CRUDBaseHibernateDAOImpl implements UseryDao {
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		
		return Usery.class;
	}


}
