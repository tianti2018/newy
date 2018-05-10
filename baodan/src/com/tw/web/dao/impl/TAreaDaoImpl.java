package com.tw.web.dao.impl;
import org.springframework.stereotype.Repository;

import com.tw.web.dao.TAreaDao;
import com.tw.web.hibernate.persistent.TArea;
@Repository
public class TAreaDaoImpl extends CRUDBaseHibernateDAOImpl  implements TAreaDao {

	@Override
	protected Class getPojoClass() {
		return TArea.class;
	}

}
