package com.tw.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.tw.web.dao.MessageDAO;
import com.tw.web.hibernate.persistent.Message;

@Repository
public class MessageDAOImpl extends CRUDBaseHibernateDAOImpl implements MessageDAO {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getPojoClass() {
		return Message.class;
	}
	
}
