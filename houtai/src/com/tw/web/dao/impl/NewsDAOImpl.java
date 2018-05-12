package com.tw.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.tw.web.dao.NewsDAO;
import com.tw.web.hibernate.persistent.News;

@Repository
public class NewsDAOImpl extends CRUDBaseHibernateDAOImpl implements NewsDAO {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		return News.class;
	}
	
}
