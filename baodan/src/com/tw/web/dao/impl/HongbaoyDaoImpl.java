package com.tw.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.tw.web.dao.HongbaoyDao;
import com.tw.web.hibernate.persistent.Hongbaoy;

@Repository
public class HongbaoyDaoImpl extends CRUDBaseHibernateDAOImpl implements HongbaoyDao {

	@Override
	protected Class getPojoClass() {
		return Hongbaoy.class;
	}

}
